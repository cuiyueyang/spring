package com.example.demo.controller;

import com.example.demo.common.ApiResponseEntity;
import com.example.demo.service.OpenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: </p>
 * <p>@date 2022/8/29 9:46</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@RestController
@RequestMapping("/openTest")
@Api("调用openTest测试")
public class OpenController {

    @Autowired
    private OpenService openService;

    @ApiOperation("测试调用第三方接口配置")
    @GetMapping("/test")
    public ApiResponseEntity test2() {
        return openService.test2();
    }

}
