package com.ls.scheduledemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1023:52
 */
@EnableScheduling
@SpringBootApplication
public class ScheduleDemoApplication {

    public static void main(String[] args){
        SpringApplication.run(ScheduleDemoApplication.class, args);
    }
}
