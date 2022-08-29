package com.example.demo.service;


import com.example.demo.domain.StudentInfo;
import com.example.demo.domain.Vehicle;

import java.util.HashMap;
import java.util.List;

/**
 * <p>Description: testService</p>
 * <p>Date: 2021/7/14 10:28 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */

public interface TestService {

    StudentInfo test1();

    StudentInfo test2();

    StudentInfo test3();

    String test4();

    String test5();

    StudentInfo test6();

    /**
     * 测试自定义注解判断，是否符合规范
     * @return
     */
    StudentInfo test7() throws Exception;

    /**
     * 测试feign
     * @param vehicle
     */
    void testFeign(Vehicle vehicle);

    void testSw();

    void identity();

    HashMap<Double, Double> returnTest1();

    Object[] returnTest2();
}