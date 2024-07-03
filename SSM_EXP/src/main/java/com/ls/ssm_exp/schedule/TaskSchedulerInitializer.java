package com.ls.ssm_exp.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import org.springframework.core.annotation.Order;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/3019:37
 */
@Component
@Order(2)
public class TaskSchedulerInitializer implements ApplicationRunner {

    @Autowired
    private final TaskScheduler taskScheduler;

    public TaskSchedulerInitializer(TaskScheduler taskScheduler) {
        this.taskScheduler = taskScheduler;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        taskScheduler.schedule(this::task, new CronTrigger("0/5 * * * * ?")) ;
    }

    private void task() {
        System.out.println("执行任务...") ;
    }

//    @Scheduled(cron = "0/1 * * * * ?")
//    public void task0() {
//        System.out.println("执行任务2...");
//    }
}
