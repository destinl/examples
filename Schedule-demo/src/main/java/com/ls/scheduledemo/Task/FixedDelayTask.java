package com.ls.scheduledemo.Task;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1023:54
 */
public class FixedDelayTask {

    @Scheduled(fixedDelay = 15000)
    public void performDelayedTask() {
        System.out.println("Fixed delay task executed at " + System.currentTimeMillis());
    }
}
