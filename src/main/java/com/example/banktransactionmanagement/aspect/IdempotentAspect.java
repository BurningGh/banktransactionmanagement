package com.example.banktransactionmanagement.aspect;


import com.example.banktransactionmanagement.exception.ErrorEnum;
import com.example.banktransactionmanagement.exception.TransactionSystemException;
import com.example.banktransactionmanagement.exception.TransactionUserException;
import com.example.banktransactionmanagement.util.MD5Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 幂等操作
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Aspect
@RequiredArgsConstructor
@Slf4j
@Component
public class IdempotentAspect {

    public static final String UTF_8 = "UTF-8";

    private final IRedisCmd redisCmd;

    /**
     * 幂等过期时间
     */
    @Value("${idempotent.expireTime:600}")
    private int expireTime;

    /**
     * 锁失败的过期时间 s
     */
    @Value("${idempotent.lockFailExpireTime:10}")
    private int lockFailExpireTime;

    private final ExpressionParser parser = new SpelExpressionParser();

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController) && execution(public * *(..))")
    public void pointCut() {

    }

    private final Map<Method, Optional<Idempotent>> methodIdempotentMap = new ConcurrentHashMap<>(128);

    private Idempotent getIdempotent(Method method) {
        //使用缓存
        Optional<Idempotent> idempotentOptional = methodIdempotentMap.get(method);
        if (idempotentOptional != null) {
            return idempotentOptional.orElse(null);
        } else {
            //优先从方法上取注解，其次从类
            Idempotent idempotent = method.getAnnotation(Idempotent.class);
            if (idempotent == null) {
                Class<?> clazz = method.getDeclaringClass();
                idempotent = clazz.getAnnotation(Idempotent.class);

            }
            // 获取类上的注解
            Optional<Idempotent> idempotentOp = Optional.ofNullable(idempotent);
            methodIdempotentMap.put(method, idempotentOp);
            return idempotentOp.orElse(null);
        }
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        String className = method.getDeclaringClass().getSimpleName();
        //优先从方法上取注解，其次从类
        Idempotent idempotent = getIdempotent(method);
        if (idempotent != null) {
            String key = generateKey(className + ":" + method.getName(), idempotent, point.getArgs());
            //判断是否已经存在幂等key
            boolean setSuccess = redisCmd.setNx(key, lockFailExpireTime);
            if (!setSuccess) {
                //返回重复请求
                return new TransactionUserException(ErrorEnum.REQUEST_REPEATED);
            }
            try {
                Object proceed = point.proceed();
                int expire = expireTime;
                if (StringUtils.hasText(idempotent.expire())) {
                    expire = Integer.parseInt(idempotent.expire());
                }
                //执行完需要延长key时间至指定的幂等过期时间
                redisCmd.expire(key, expire);
                return proceed;
            } catch (Throwable throwable) {
                //支持重试的话，需要删去幂等key
                if (idempotent.retry()) {
                    log.warn("执行失败，删除幂等key:{}", key);
                    redisCmd.del(key);
                }
                throw throwable;
            }
        }
        return point.proceed();
    }

    String generateKey(String methodName, Idempotent idempotent, Object[] args) {
        StringBuilder key = new StringBuilder(methodName);
        if (KeyStrategy.PARAM_SPECIFIC.equals(idempotent.keyStrategy())) {
            StandardEvaluationContext context = new StandardEvaluationContext();
            for (int i = 0; i < args.length; i++) {
                context.setVariable("arg" + i, args[i]);
            }
            key.append(parser.parseExpression(idempotent.key()).getValue(context, String.class));
        } else if (KeyStrategy.PARAM_MD5.equals(idempotent.keyStrategy())) {
            key.append(Arrays.stream(args).filter(Objects::nonNull).map(a -> {
                if (a instanceof String) {
                    return (String) a;
                } else return a.toString();
            }).collect(Collectors.joining(":")));
        }else {
            throw  new TransactionSystemException(ErrorEnum.TRANSACTION_SYSTEM_ERROR,"接口幂等配置错误");
        }
        return MD5Utils.md5Hex(key.toString(), UTF_8);
    }

}


