package com.example.demo.controller;

import com.example.demo.common.ApiResponseEntity;
import com.example.demo.service.BigDataService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: 大数据处理</p>
 * <p>Date: 2021/9/18 10:08 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@Api("大数据处理")
@RestController
@Slf4j
@RequestMapping("bigdataControler")
public class BigDataController {

    @Autowired
    private BigDataService bigDataService;

    @GetMapping("jdbcTest")
    public void jdbcTest() {
        bigDataService.jdbcTest();
    }

    @GetMapping("jdbcBatchTest")
    public void jdbcBatchTest() {
        bigDataService.jdbcBatchTest();
    }

    @GetMapping("JdbcTemplateTest")
    public void JdbcTemplateTest() {
        bigDataService.JdbcTemplateTest();
    }

    @GetMapping("jpaTest")
    public void jpaTest() {
        bigDataService.jpaTest();
    }

    @GetMapping("threadPoolTest")
    public void threadPoolTest() {
        bigDataService.threadPoolTest();
    }

    @ApiResponses({})
    @ApiImplicitParams({})
    @ApiOperation(value = "制造大数据")
    @GetMapping("makeBigData")
    public ApiResponseEntity makeBigData(@ApiParam("插入条数") int num) {
        bigDataService.makeBigData(num);
        return ApiResponseEntity.success();
    }

    @ApiResponses({})
    @ApiImplicitParams({})
    @ApiOperation(value = "制造大数据2")
    @GetMapping("makeBigData2")
    public ApiResponseEntity makeBigData2(@ApiParam("插入条数") int num) {
        bigDataService.makeBigData2(num);
        return ApiResponseEntity.success();
    }

    @ApiResponses({})
    @ApiImplicitParams({})
    @ApiOperation(value = "制造大数据3")
    @GetMapping("makeBigData3")
    public ApiResponseEntity makeBigData3(@ApiParam("插入条数") int num) {
        bigDataService.makeBigData3(num);
        return ApiResponseEntity.success();
    }

    @ApiResponses({})
    @ApiImplicitParams({})
    @ApiOperation(value = "制造大数据4")
    @GetMapping("makeBigData4")
    public ApiResponseEntity makeBigData4(@ApiParam(value = "插入条数") Integer num) {
        bigDataService.makeBigData4(num);
        return ApiResponseEntity.success();
    }

    @ApiResponses({})
    @ApiImplicitParams({})
    @ApiOperation(value = "批量更新")
    @GetMapping("updateBigData1")
    public ApiResponseEntity updateBigData1(@ApiParam(value = "插入条数") Integer num) {
        bigDataService.updateBigData1(num);
        return ApiResponseEntity.success();
    }

}
