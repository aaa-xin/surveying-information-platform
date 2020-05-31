package com.aaa.qy108.dynamic.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author guohang
 * @Description 登录日志的自定义注解
 * 1.@Target:标识了该注解所使用的位置(所使用的范围)
 * 2.Retention:标识了该注解什么时候生效
 * @Date 2020/5/27 20:21
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LogAnnotation {

    /**
     * 需要执行的操作类型
     * @return
     */
    String operationType() default "";


    /**
     * 需要执行的操作名称
     * @return
     */
    String operationName() default "";


}
