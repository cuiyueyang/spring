package com.example.demo.web.authority.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>Description: 是否符合规范</p>
 * <p>Date: 2021/8/3 11:19 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsStandard {

    /**
     * ElementType:用于描述注解的使用范围（即：被描述的注解可以用在什么地方）
     *  CONSTRUCTOR:用于描述构造器
     *  FIELD:用于描述域
     *  LOCAL_VARIABLE:用于描述局部变量
     *  METHOD:用于描述方法
     *  PACKAGE:用于描述包
     *  PARAMETER:用于描述参数
     *  TYPE:用于描述类，接口，enum声明
     *
     * RetentionPolicy:表示需要在什么级别保存该注释信息，用于描述注解的生命周期（即：被描述的注解在什么范围内有效）
     *  SOURCE:在源文件中有效（即源文件保留）
     *  CLASS:在class文件中有效（即class保留）
     *  RUNTIME:在运行时有效（即运行时保留）
     */

    /**描述*/
    String describe();
    /**年龄判断*/
    long checkAge();
}
