package com.example.demo.config;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class BeanConfig {

    @Bean(name="threadPoolExecutor")
    public ThreadPoolExecutor processDataThreadPool() {
        return new ThreadPoolExecutor(6, 7, 60L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1000),
                new BasicThreadFactory.Builder().namingPattern("Thread-processData-%d").build(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    @Bean("problemVehicle")
    public Executor passToCity() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(200);
        executor.setKeepAliveSeconds(60);
        executor.setThreadNamePrefix("problemVehicle-");
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

}