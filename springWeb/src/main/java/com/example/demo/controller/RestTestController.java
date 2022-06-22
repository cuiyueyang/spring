package com.example.demo.controller;

import com.example.demo.common.ApiResponseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: rest风格接口 </p>
 * <p>@date 2021/11/22 9:36</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@RestController
@RequestMapping("/restTest")
@Slf4j
@Api("rest风格接口测试")
public class RestTestController {

    @ApiOperation("test1")
    @RequestMapping("/test1")
    public ApiResponseEntity test1() {
        return ApiResponseEntity.success();
    }

}
