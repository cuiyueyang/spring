package com.example.demo.controller;

import com.example.demo.common.ApiResponseEntity;
import com.example.demo.service.AmapService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>Description: </p>
 * <p>Date: 2023/12/27 10:03</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@Api("地图测试")
@RestController
@Slf4j
@RequestMapping("/amap")
public class AmapController {

    @Autowired
    private AmapService amapService;

    @ApiOperation(value = "计算距离，经纬度")
    @GetMapping("/direction")
    public ApiResponseEntity direction(String lon1, String lat1,
                                          String lon2, String lat2) {
        return amapService.direction(lon1, lat1, lon2, lat2);
    }

}
