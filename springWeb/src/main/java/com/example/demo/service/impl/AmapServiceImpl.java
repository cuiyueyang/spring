package com.example.demo.service.impl;

import com.example.demo.common.ApiResponseEntity;
import com.example.demo.domain.DirectionResponse;
import com.example.demo.service.AmapService;
import com.example.demo.util.AmapUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Description: </p>
 * <p>Date: 2023/12/27 10:11</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@Slf4j
@Service
public class AmapServiceImpl implements AmapService {

    @Autowired
    private AmapUtil amapUtil;

    @Override
    public ApiResponseEntity direction(String lon1, String lat1, String lon2, String lat2) {
        DirectionResponse directionResponse = amapUtil.direction(lon1, lat1, lon2, lat2);
        int distance = directionResponse.getDistance();
        int duration = directionResponse.getDuration()/60;
        log.info("距离{}米，预计{}分钟",distance, duration);
        return ApiResponseEntity.success();
    }
}
