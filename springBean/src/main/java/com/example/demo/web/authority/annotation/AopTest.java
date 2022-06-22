package com.example.demo.web.authority.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Description: aop自定义注解</p>
 * <p>Date: 2021/7/28 13:53 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AopTest {
}
