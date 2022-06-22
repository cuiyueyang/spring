package com.example.demo.web.client;

import com.example.demo.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * <p>Description: </p>
 * <p>Date: 2021/9/22 11:17 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@FeignClient(name = "openClient", url = "${server-url.open}", configuration = FeignConfiguration.class)
public interface OpenClient {

    @GetMapping("/open/test")
    String testFeignClient();

}
