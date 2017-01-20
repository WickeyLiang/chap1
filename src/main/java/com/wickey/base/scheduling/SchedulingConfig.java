package com.wickey.base.scheduling;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Created by fatboyliang on 2016/8/15.
 */

@Configuration
@EnableScheduling
public class SchedulingConfig {


    @Scheduled(cron = "0/10 * * * * ?") //每20秒执行1次
    public void schedulerTest(){
        System.out.println("I am running");

    }
}
