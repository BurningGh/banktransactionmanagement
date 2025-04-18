package com.example.banktransactionmanagement.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 日志切面
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Aspect
@Component
public class LogAspect {

    /**
     * 警告阈值，超过这个阈值会记录警告信息
     */
    @Value("${logging.warn-threshold:10000}")
    private long warnThreshold;

    @Pointcut("within(com.example.banktransactionmanagement.interfaces.controller..*)")
    public void controllerMethods() {
    }

    @Around("controllerMethods()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取日志实例，便于记录切面类
        Logger logger = LoggerFactory.getLogger(joinPoint.getTarget().getClass());

        Signature signature = joinPoint.getSignature();
        String methodName = signature.toShortString();

        StringBuilder argStr = getArgStr(joinPoint);
        long startTime = System.currentTimeMillis();
        // 交易的安全性需要脱敏，这里供演示暂时不写
        logger.info("Method {} started with arguments: {}", methodName, argStr);
        try {
            Object result = joinPoint.proceed();

            long cost = System.currentTimeMillis() - startTime;
            if (cost > warnThreshold) {
                // 执行时间过长的需要记录警告信息
                // 交易的安全性需要脱敏，这里供演示暂时不写
                logger.warn("Method {} completed in {} ms with result: {}", methodName, cost, result);
            } else {
                // 交易的安全性需要脱敏，这里供演示暂时不写
                logger.info("Method {} completed in {} ms with result: {}", methodName, cost, result);
            }
            return result;
        } catch (Throwable e) {
            logger.error("Method {} failed in {} ms with exception: {}", methodName, System.currentTimeMillis() - startTime, e.getMessage(), e);
            // 重新抛出异常，确保调用方能够处理
            throw e;
        }
    }

    public static StringBuilder getArgStr(ProceedingJoinPoint joinPoint) {
        StringBuilder argStr = new StringBuilder();

        Object[] values = joinPoint.getArgs();
        if (values == null || values.length == 0) {
            argStr.append("No arguments");
        } else if (values.length == 1) {
            argStr.append(values[0]);
        } else {
            //多参数的需要记录参数名称
            String[] names = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
            for (int i = 0; i < names.length; i++) {
                argStr.append(names[i]).append("=").append(values[i]).append(",");
            }
        }
        return argStr;
    }

}
    