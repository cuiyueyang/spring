package com.example.demo.service.impl;

import com.example.demo.dao.TestDao;
import com.example.demo.domain.StudentInfo;
import com.example.demo.linkUtils.collection.CollectionUtil;
import com.example.demo.service.EhCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>Description: </p>
 * <p>@date 2021/12/27 10:41</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@Service
public class EhCacheServiceImpl implements EhCacheService {

    @Autowired
    private TestDao testDao;

    @Override
    public StudentInfo queryById(String id) {
        StudentInfo studentInfo = testDao.findById(id).orElse(null);
        return studentInfo;
    }

    @Override
    public void update(StudentInfo studentInfo) {
        testDao.save(studentInfo);
    }

    @Override
    public StudentInfo queryByIdCache(String id) {
        StudentInfo studentInfo = testDao.findById(id).orElse(null);
        return studentInfo;
    }

    @Override
    public void updateCache(StudentInfo studentInfo) {
        testDao.save(studentInfo);
    }
}
