package com.ls.scheduledemo.Task;

import org.springframework.scheduling.annotation.Scheduled;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1023:54
 */
public class CronTask {
    @Scheduled(cron = "0 0/2 * * * ?")
    public void performCronTask() {
        System.out.println("Cron task executed at " + System.currentTimeMillis());
    }
}
