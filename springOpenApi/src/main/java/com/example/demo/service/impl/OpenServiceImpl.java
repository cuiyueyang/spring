package com.example.demo.service.impl;

import com.example.demo.mapper.TestMapper;
import com.example.demo.service.OpenService;
import com.example.demo.dao.TestDao;
import com.example.demo.domain.StudentInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: </p>
 * <p>Date: 2021/9/22 17:09 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@Service
@Slf4j
public class OpenServiceImpl implements OpenService {

    @Autowired
    private TestMapper testMapper;

    @Override
    public void test() {
        Integer count = testMapper.test();
        System.out.println(count);
    }
}
