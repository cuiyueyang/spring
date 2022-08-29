package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;

@ComponentScan("com.example")
@EnableFeignClients(basePackages={"com.example.demo.**.client"})
@EnableJpaAuditing
@ComponentScan("com.example")
@EntityScan(basePackages = {"com.example.demo.domain"})
@MapperScan(basePackages = {"com.example.demo.mapper"})
@SpringBootApplication
@EnableAsync
public class SpringWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebApplication.class, args);
    }

}
