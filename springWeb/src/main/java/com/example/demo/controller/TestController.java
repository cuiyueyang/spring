package com.example.demo.controller;

import com.example.demo.common.ApiResponseEntity;
import com.example.demo.domain.Person;
import com.example.demo.domain.Vehicle;
import com.example.demo.service.TestService;
import com.example.demo.util.BatchHandler;
import com.example.demo.util.ThreadPool;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * <p>Description: 测试</p>
 * <p>Date: 2021/7/14 10:15 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */

@Api("测试")
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @GetMapping("demo1")
    public String demo1() {
        return testService.test1().toString();
    }

    @GetMapping("demo2")
    public String demo2() {
        return testService.test2().toString();
    }

    @GetMapping("demo3")
    public String demo3() {
        return testService.test3().toString();
    }

    @GetMapping("demo4")
    public String demo4() {
        return testService.test4();
    }

    @GetMapping("demo5")
    public String testAsync() {
        return testService.test5();
    }

    @GetMapping("demo6")
    public ApiResponseEntity demo6(){
        try {
            return ApiResponseEntity.success(testService.test2());
        } catch (Exception e) {
            return ApiResponseEntity.fail(e.getMessage());
        }
    }

    @GetMapping("testApi")
    @ApiOperation(value = "测试返回模板值")
    public ApiResponseEntity testApi(@ApiParam("返回值标识") boolean flag) {
        try {
            if (flag) {
                return ApiResponseEntity.success(testService.test7());
            } else {
                return ApiResponseEntity.fail("出错了！");
            }
        } catch (Exception e) {
            return ApiResponseEntity.fail(e.getMessage());
        }
    }

    @PostMapping("testFeign")
    @ApiOperation(("更新省外行驶证信息"))
    public ApiResponseEntity testFeign(@RequestBody Vehicle vehicle) {
        try {
            testService.testFeign(vehicle);
            return ApiResponseEntity.success();
        } catch (Exception e) {
            return ApiResponseEntity.fail(e.getMessage());
        }
    }

    @GetMapping("batchTest")
    @ApiOperation(("批量处理"))
    public String batchTest() {
        List<String> resList = Arrays.asList("0", "1", "2", "3", "4", "5", "6",
          "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
          "18", "19", "20", "21", "22", "23", "24", "25", "26", "27",
          "28", "29", "30", "31", "32", "33", "34", "35", "36", "37",
          "38", "39", "40", "41", "42", "43", "44", "45", "46", "47",
          "48", "49", "50", "51", "52", "53", "54", "55", "56", "57",
          "58", "59", "60", "61", "62", "63", "64", "65", "66", "67",
          "68", "69", "70", "71", "72", "73", "74", "75", "76", "77",
          "78", "79", "80", "81", "82", "83", "84", "85", "86", "87",
          "88", "89", "90", "91", "92", "93", "94", "95", "96", "97",
          "98","99","100");
        resList = new ArrayList<>(resList);
        ThreadPool threadPool = ThreadPool.getThreadPool();
        new BatchHandler<String>().batchResolve(resList,10, items -> {threadPool.execute(() -> System.out.println("-----"+items));});
        return "success";
    }

    @GetMapping("testSw")
    @ApiOperation(("事务"))
    public void testSw() {
        testService.testSw();
    }


    @PostMapping("/ding")
    @ApiOperation(value = "政务钉免登授权")
    public String loginNewByDing(String code, String name, @RequestBody Person person) {
        return "success";
    }

    @GetMapping("identity")
    @ApiOperation(("自增主键测试"))
    public void identity() {
        testService.identity();
    }

    @GetMapping("returnTest1")
    @ApiOperation(("返回类型测试1"))
    public HashMap<Double, Double> returnTest1() {
        return testService.returnTest1();
    }

    @GetMapping("returnTest2")
    @ApiOperation(("返回类型测试2"))
    public Object[] returnTest2() {
        return testService.returnTest2();
    }


}
