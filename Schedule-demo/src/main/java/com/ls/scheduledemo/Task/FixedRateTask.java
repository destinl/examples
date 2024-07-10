package com.ls.scheduledemo.Task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1023:48
 */
@Component
public class FixedRateTask {
    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("Current Time: " + System.currentTimeMillis());
    }
}
