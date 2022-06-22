package com.example.demo.service.impl;

import com.example.demo.service.OpenService;
import com.example.demo.web.client.OpenClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public String test() {
        return openClient.testFeignClient();
    }
}
