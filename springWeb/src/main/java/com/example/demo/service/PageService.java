package com.example.demo.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.domain.StudentInfo;

/**
 * <p>Description: 分页测试</p>
 * <p>Date: 2021/9/16 15:44 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
public interface PageService {

    IPage<StudentInfo> demo1();

    IPage<StudentInfo> demo2();
}
