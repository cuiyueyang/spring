package com.example.demo.TimeJob;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * <p>Description: </p>
 * <p>Date: 2024/01/29 14:04</p>
 *
 * @author cuiyy
 * @version v1.0.0
 **/
@Slf4j
@Component
public class TimeJob {

    @Async("problemVehicle")
    @Scheduled(cron = "${cron.test1}")
    public void test1() throws Exception {
        log.info("test1-1");
        Thread.sleep(10000000 * 1000);
        log.info("test1-2");
    }

    @Scheduled(cron = "${cron.test2}")
    public void test2() throws Exception {
        log.info("test2-1");
        Thread.sleep(10 * 1000);
        log.info("test2-2");
    }

}
