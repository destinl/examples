package com.ls.ssm_exp.listener;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/2621:23
 */
public class MyEventListener {
//    使用 @EventListener 注解监听容器启动事件
    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        // 在容器启动完成后执行操作
        System.out.println("Spring容器已启动，所有Bean加载完成");
    }
}
