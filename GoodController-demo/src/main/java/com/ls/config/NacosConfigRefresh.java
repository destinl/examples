//package com.ls.config;
//
//import com.alibaba.nacos.api.config.ConfigService;
//import com.alibaba.nacos.api.config.listener.Listener;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cloud.context.config.annotation.RefreshScope;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.Executor;
///**
// * @Description:
// * @Author: ls
// * @Date: 2024/8/7 22:59
// */
//@Component
//@RefreshScope  // 启用配置刷新
//public class NacosConfigRefresh {
//
//    @Autowired
//    private ConfigService configService;
//
//    @Autowired
//    private ThreadPoolConfig threadPoolConfig;
//
//    public void registerConfigListener() {
//        try {
//            configService.addListener("threadpool", threadPoolConfig.getGroup(), new Listener() {
//                @Override
//                public void receiveConfigInfo(String configInfo) {
//                    // 当配置发生变化时，重新加载线程池配置
//                    threadPoolConfig.reloadConfig(configInfo);
//                }
//
//                @Override
//                public Executor getExecutor() {
//                    return null;
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}