package com.ls.scheduledemo.Task;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

//import javax.annotation.PostConstruct;
import java.util.concurrent.ScheduledFuture;

/**
 * @Description: 动态修改任务执行时间 借助Spring的TaskScheduler接口和ScheduledFuture对象。
 * @Author: ls
 * @Date: 2024/7/1023:55
 */
@Component
public class DynamicScheduledTask {

    @Autowired
    private TaskScheduler taskScheduler;

    private ScheduledFuture<?> scheduledFuture;

    @PostConstruct
    public void scheduleTask() {
        scheduledFuture = taskScheduler.scheduleAtFixedRate(this::performTask, 5000);
    }

    public void changeTaskInterval(long interval) {
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        scheduledFuture = taskScheduler.scheduleAtFixedRate(this::performTask, interval);
    }

    private void performTask() {
        System.out.println("Dynamic scheduled task executed at " + System.currentTimeMillis());
    }
}
