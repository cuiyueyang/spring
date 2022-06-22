package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.dao.TestDao;
import com.example.demo.domain.StudentInfo;
import com.example.demo.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Description: 分页测试</p>
 * <p>Date: 2021/9/16 15:44 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@Service
@Slf4j
public class PageServiceImpl implements PageService {

    @Autowired
    private TestDao testDao;

    @Override
    public IPage<StudentInfo> demo1() {
        List<StudentInfo> studentInfos = testDao.findAll();
        IPage<StudentInfo> page = new Page<>();
        page.setRecords(studentInfos);
        page.setSize(studentInfos.size());
        return page;
    }

    @Override
    public IPage<StudentInfo> demo2() {
        StudentInfo studentInfo = new StudentInfo();
        return null;
    }

}
