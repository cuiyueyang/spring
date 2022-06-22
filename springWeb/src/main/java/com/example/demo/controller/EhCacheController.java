package com.example.demo.controller;

import com.example.demo.common.ApiResponseEntity;
import com.example.demo.domain.StudentInfo;
import com.example.demo.service.EhCacheService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: ehcache缓存测试</p>
 * <p>@date 2021/12/27 10:47</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@RestController
@Slf4j
@RequestMapping("EhCacheController")
@Api("ehcache缓存测试")
public class EhCacheController {

    @Autowired
    private EhCacheService ehCacheService;

    @ApiOperation("根据id查询cache")
    @GetMapping("/queryByIdCache")
    public StudentInfo queryByIdCache(String id) {
        return ehCacheService.queryByIdCache(id);
    }

    @ApiOperation("根据id查询")
    @GetMapping("/queryById")
    public StudentInfo queryById(String id) {
        return ehCacheService.queryById(id);
    }

    @ApiOperation("更新或新增")
    @PostMapping("/update")
    public ApiResponseEntity update(StudentInfo studentInfo) {
        ehCacheService.update(studentInfo);
        return ApiResponseEntity.success();
    }

    @ApiOperation("更新或新增Cache")
    @PostMapping("/updateCache")
    public ApiResponseEntity updateCache(StudentInfo studentInfo) {
        ehCacheService.updateCache(studentInfo);
        return ApiResponseEntity.success();
    }

}
