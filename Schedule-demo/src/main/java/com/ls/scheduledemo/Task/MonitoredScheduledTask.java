package com.ls.scheduledemo.Task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledFuture;

/**
 * @Description: 任务状态监控 通过ScheduledFuture对象来监控任务的状态，例如取消任务、检查任务是否完成等。
 * @Author: ls
 * @Date: 2024/7/110:00
 */
@Component
public class MonitoredScheduledTask {

    @Autowired
    private TaskScheduler taskScheduler;

    private ScheduledFuture<?> scheduledFuture;

    @PostConstruct
    public void scheduleTask() {
        scheduledFuture = taskScheduler.scheduleAtFixedRate(this::performTask, 10000);
    }

    public void cancelTask() {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
    }

    private void performTask() {
        System.out.println("Monitored scheduled task executed at " + System.currentTimeMillis());
    }
}
