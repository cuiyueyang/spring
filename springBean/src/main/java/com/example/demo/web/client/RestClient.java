package com.example.demo.web.client;

import com.example.demo.common.ApiResponseEntity;
import com.example.demo.config.FeignConfiguration;
import com.example.demo.domain.Vehicle;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * <p>Description: Feign远程调用接口</p>
 * <p>Date: 2021/8/5 15:14 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@FeignClient(name = "restClient", url = "${server-url.rest}", configuration = FeignConfiguration.class)
public interface RestClient {
    @PostMapping("/api/vehicle/pushArrestVehicle")
    ApiResponseEntity testFeignClient(@RequestBody Vehicle vehicle);
}
