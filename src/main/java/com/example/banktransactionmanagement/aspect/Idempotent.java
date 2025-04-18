package com.example.banktransactionmanagement.aspect;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static com.example.banktransactionmanagement.aspect.KeyStrategy.PARAM_MD5;


/**
 * 幂等
 *
 * @author DENGWENJIAN1
 * @date 2025/4/17
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface Idempotent {

    /**
     * 过期时间  s
     */
    String expire() default "";

    /**
     * 异常时是否可以重试
     */
    boolean retry() default true;

    /**
     * 支持springEl表达式，#arg0+':'+#arg1
     */
    KeyStrategy keyStrategy() default PARAM_MD5;

    /**
     * 支持springEl表达式，#arg0+':'+#arg1
     */
    String key() default "#arg0";






}


