package com.example.demo.controller;

import com.example.demo.common.ApiResponseEntity;
import com.example.demo.domain.StudentInfo;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Description: swaggerApi test</p>
 * <p>@date 2021/11/10 11:04</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@Api(tags = "swagger注解测试")
@RestController
@RequestMapping("swaggerApi")
@Slf4j
public class SwaggerApiController {


    /**
     *paramType：表示参数放在哪个地方
     *     header-->请求参数的获取：@RequestHeader(代码中接收注解)
     *     query-->请求参数的获取：@RequestParam(代码中接收注解)
     *     path（用于restful接口）-->请求参数的获取：@PathVariable(代码中接收注解)
     *     body-->请求参数的获取：@RequestBody(代码中接收注解)
     *     form（不常用）
     *
     *     query 用于 get 不需要配合参数
     *     path 用于 post restful 风格的接口， 配合@PathVariable使用
     */

    @ApiResponses({
            @ApiResponse(code = 200, message = "code,返回值注解")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id", name = "id", required = true, paramType = "query", dataType = "string", dataTypeClass = String.class)
    })
    @ApiOperation(value = "test1测试", notes = "接口注解", httpMethod = "GET")
    @GetMapping("test1")
    public ApiResponseEntity test1(String id) {
        return ApiResponseEntity.success();
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "code,返回值注解")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(value = "id", name = "id", required = true, paramType = "path", dataType = "string", dataTypeClass = String.class)
    })
    @ApiOperation(value = "test2测试", notes = "接口注解", httpMethod = "POST")
    @PostMapping("test2/{id}")
    public ApiResponseEntity test2(@PathVariable("id") String id) {
        return ApiResponseEntity.success();
    }

    @ApiResponses({
            @ApiResponse(code = 200, message = "code,返回值注解")
    })
    @ApiImplicitParams({
            @ApiImplicitParam(value = "studentInfo", name = "studentInfo", required = true, paramType = "body", dataType = "StudentInfo", dataTypeClass = StudentInfo.class)
    })
    @ApiOperation(value = "test3测试", notes = "接口注解", httpMethod = "POST")
    @PostMapping("test3")
    public ApiResponseEntity test3(@RequestBody StudentInfo studentInfo) {
        return ApiResponseEntity.success();
    }

}
