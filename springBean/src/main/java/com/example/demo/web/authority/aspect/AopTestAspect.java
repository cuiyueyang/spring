package com.example.demo.web.authority.aspect;

import com.example.demo.dao.TestDao;
import com.example.demo.domain.StudentInfo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>Description: aop 切面编程</p>
 * <p>Date: 2021/7/28 13:52 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@Aspect
@Component
@Slf4j
public class AopTestAspect {

    @Autowired
    private TestDao testDao;

    @Pointcut("@annotation(com.example.demo.web.authority.annotation.AopTest)")
    public void aopTest() {
    }
    @Pointcut("@annotation(com.example.demo.web.authority.annotation.AopAround)")
    public void aopAround() {
    }

    @Before("aopTest()")
    public void before() {
        System.out.println("hello aop!");
    }

    @After("aopTest()")
    public void after() {
        System.out.println("bye aop!");
    }

    @AfterReturning("aopTest()")
    public void afterReturn() {
        System.out.println("hello return!");
    }

    @AfterThrowing("aopTest()")
    public StudentInfo afterThrowing() {
        System.out.println("hello throwing!");
        return testDao.findById("001").orElse(null);
    }

    @Around("aopAround()")
    public StudentInfo around() {
        System.out.println("hello around!");
        return testDao.findById("001").orElse(null);
    }


}
