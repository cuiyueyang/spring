package com.example.demo.service.impl;

import com.example.demo.common.ApiResponseEntity;
import com.example.demo.service.OpenService;
import com.example.demo.web.client.OpenClient;
import com.example.demo.web.client.OpenManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * <p>Description: </p>
 * <p>Date: 2021/9/22 11:19 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@Service
@Slf4j
public class OpenServiceImpl implements OpenService {

    @Autowired
    private OpenClient openClient;

    @Value("${openUrl}")
    private String openUrl;

    @Override
    public String test() {
        return openClient.testFeignClient();
    }

    @Override
    public ApiResponseEntity test2() {
        OpenManager openManager = getOpenManager();
        openManager.testFeignClient();
        return ApiResponseEntity.success();
    }

    private OpenManager getOpenManager() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(openUrl)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(OpenManager.class);
    }
}
