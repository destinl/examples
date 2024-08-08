//package com.ls.config;
//
//import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.util.concurrent.ArrayBlockingQueue;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.ThreadPoolExecutor;
//import java.util.concurrent.TimeUnit;
//
///**
// * @Description:
// * @Author: ls
// * @Date: 2024/8/7 22:55
// */
////@Configuration
//@NacosConfigurationProperties(dataId = "order-service-dev.yml", autoRefreshed = true)
//public class ThreadPoolConfig {
//
//    @Value("${threadpool.corePoolSize}")
//    private int coreSize;
//
//    @Value("${threadpool.maxPoolSize}")
//    private int maxPoolSize;
//
//    @Value("${threadpool.queueCapacity}")
//    private int queueCapacity;
//
//    @Bean
//    public ExecutorService threadPool() {
//        return new ThreadPoolExecutor(coreSize, maxPoolSize,
//                60L, TimeUnit.SECONDS,
//                new ArrayBlockingQueue<>(queueCapacity));
//    }
//}
//
