package com.example.demo.config;

import feign.Feign;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * <p>Description: Feign config</p>
 * <p>Date: 2021/8/5 15:19 </p>
 *
 * @version v1.0.0
 * @author: cuiyy
 */
@Configuration
public class FeignConfiguration {

    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder() {
        return Feign.builder();
    }

    @Bean
    Logger.Level feignLogger() {
        return Logger.Level.FULL;
    }

}
