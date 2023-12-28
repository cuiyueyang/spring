package com.example.demo.service;

import com.example.demo.common.ApiResponseEntity;

/**
 * <p>Description: </p>
 * <p>Date: 2023/12/27 10:11</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
public interface AmapService {
    ApiResponseEntity direction(String lon1, String lat1, String lon2, String lat2);
}
