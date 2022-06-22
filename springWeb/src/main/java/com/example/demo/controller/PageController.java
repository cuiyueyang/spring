package com.example.demo.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.demo.common.ApiResponseEntity;
import com.example.demo.domain.StudentInfo;
import com.example.demo.service.PageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: 分页测试</p>
 * <p>Date: 2021/9/16 15:38 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@Api("分页测试")
@RestController
@RequestMapping("page")
@Slf4j
public class PageController {

    @Autowired
    private PageService pageService;

    @ApiOperation("demo1")
    @GetMapping("demo1")
    public IPage<StudentInfo> demo1() {
        return pageService.demo1();
    }

    @ApiOperation("demo2")
    @GetMapping("demo2")
    public ApiResponseEntity demo2() {
        return null;
    }

}
