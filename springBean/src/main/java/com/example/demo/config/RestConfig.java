package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author 程序员大佬超
 * @date 2022-03-08 16:39
 */
@Configuration
public class RestConfig {
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
