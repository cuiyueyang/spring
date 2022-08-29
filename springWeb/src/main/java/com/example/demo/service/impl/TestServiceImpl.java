package com.example.demo.service.impl;

import com.example.demo.common.ApiResponseEntity;
import com.example.demo.dao.DdDao;
import com.example.demo.dao.TestDao;
import com.example.demo.domain.Dd;
import com.example.demo.domain.StudentInfo;
import com.example.demo.domain.Vehicle;
import com.example.demo.mapper.TestMapper;
import com.example.demo.service.TestService;
import com.example.demo.web.authority.annotation.AopAround;
import com.example.demo.web.authority.annotation.AopTest;
import com.example.demo.web.authority.annotation.IsStandard;
import com.example.demo.web.client.RestClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <p>Description: 事务层</p>
 * <p>Date: 2021/7/14 10:31 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@Slf4j
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;
    @Resource
    private TestMapper testMapper;
    @Autowired
    private RestClient restClient;
    @Autowired
    private DdDao ddDao;

    /**
     * 异步和切面不冲突，切面也可以是异步的
     * @return
     */
    @AopAround
    @Async
    @Override
    public StudentInfo test1() {
        log.info("info!!!!!!!!!!!!!!");
        log.warn("warn!!!!!!!!!!!!!!");
        log.debug("debug!!!!!!!!!!!!");
        log.error("error!!!!!!!!!!!!");
        //注解形式
        return testDao.selectDemo();
    }

    @Override
    public StudentInfo test2() {
        //jpa
        return testDao.findById("001").orElse(null);
    }

    @Override
    public StudentInfo test3() {
        //mapper.xml格式
        return testMapper.test007();
    }

    @Override
    public String test4() {
        return "success";
    }


    /**
     * 异步操作 :多用于与第三方系统的交互，可降低迟缓，提高效率
     * 无返回值
     * @return
     */
    @AopAround
    @Async
    @Override
    public String test5() {
        System.out.println("hello async!");
        StudentInfo studentInfo = new StudentInfo("牛有道",15L,1L,null, null, 0);
        testDao.save(studentInfo);
        String id = studentInfo.getId();
        System.out.println(id);
        return id;
    }

    /**
     * 测试切面中的异常
     * @return
     */
    @AopTest
    @Override
    public StudentInfo test6() {
        String str = null;
        if(str.equals(22)) {
            System.out.println("-------------1-----------");
        }
        return testDao.findById("999").orElse(null);
    }

    @Override
    public StudentInfo test7() throws Exception {
        StudentInfo studentInfo = testDao.findById("001").orElse(null);
        long age = studentInfo.getStuAge();
        Field[] fields = StudentInfo.class.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(IsStandard.class)) {
                IsStandard isStandard = field.getAnnotation(IsStandard.class);
                String describe = isStandard.describe();
                long checkAge = isStandard.checkAge();
                if (checkAge > age) {
                    throw new Exception(describe);
                }
            }
        }
        return studentInfo;
    }

    @Override
    public void testFeign(Vehicle vehicle) {
        try {
            ApiResponseEntity apiResponseEntity = restClient.testFeignClient(vehicle);
            if (apiResponseEntity.getSuccess()) {
                log.info("更新行驶证信息成功");
            } else {
                log.info("更新行驶证信息失败");
            }
        } catch (Exception e) {
            log.info("调用接口异常：{}",e.getMessage());
            e.printStackTrace();
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void testSw() {
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setId("cs00111");
        studentInfo.setStuSex(1L);
        studentInfo.setStuNo("cs00111");
        studentInfo.setStuName("小顾");
        testDao.save(studentInfo);
        Integer a = null;
        if (a.equals(23)) {
            System.out.println(a);
        }
    }

    @Override
    public void identity() {
        Dd dd = new Dd();
        dd.setDd("cs");
        ddDao.save(dd);
    }

    @Override
    public HashMap<Double, Double> returnTest1() {
        HashMap<Double, Double> result = new HashMap<>();
        result.put(1.1, 2.2);
        result.put(2.2, 3.3);
        return result;
    }

    @Override
    public Object[] returnTest2() {
        Double[] result1 = new Double[2];
        result1[0] = 1.1;
        result1[1] = 2.2;
        Double[] result2 = new Double[2];
        result2[0] = 1.1;
        result2[1] = 2.2;
        Object[] result = new Object[2];
        result[0] = result1;
        result[1] = result2;
        return result;
    }



}
