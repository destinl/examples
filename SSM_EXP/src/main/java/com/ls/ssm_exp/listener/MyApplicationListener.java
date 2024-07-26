package com.ls.ssm_exp.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/2621:21
 */
public class MyApplicationListener implements ApplicationListener<ContextRefreshedEvent> {
//    怎么知道 Spring 的 Bean 加载完成？
//    使用 ApplicationListener 监听容器启动事件
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // 在容器启动完成后执行操作
        System.out.println("Spring容器已启动，所有Bean加载完成");
    }
}
