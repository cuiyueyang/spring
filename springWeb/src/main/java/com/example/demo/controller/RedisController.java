package com.example.demo.controller;

import com.example.demo.common.ApiResponseEntity;
import com.example.demo.config.RedisUtil;
import com.example.demo.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: redis测试</p>
 * <p>Date: 2021/8/17 11:00 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@Api("redis测试")
@RestController
@RequestMapping("redisController")
public class RedisController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RedisService redisService;

    @ApiOperation("缓存学生基础信息")
    @GetMapping("studentRedis")
    public ApiResponseEntity studentRedis(String stuNo) {
        try {
            return ApiResponseEntity.success(redisService.studentRedis(stuNo));
        } catch (Exception e) {
            return ApiResponseEntity.fail(e.getMessage());
        }
    }

}
