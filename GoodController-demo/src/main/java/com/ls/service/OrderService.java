package com.ls.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/27 22:37
 */
public class OrderService {
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//    @Autowired
//    private StringRedisTemplate redisTemplate;

//
//    public void createOrder(Order order) {
//        // 保存订单至数据库
//        saveOrderToDB(order);
//
//        // 将订单ID推送至延迟队列
//        rabbitTemplate.convertAndSend("orderDelayExchange", "orderDelayKey", order.getId(), message -> {
//            message.getMessageProperties().setDelay(30 * 60 * 1000); // 设置延迟时间
//            return message;
//        });

          // or redis
          // 在Redis中存储一个键，设置30分钟过期
//        redisTemplate.opsForValue().set("order:" + order.getId(), order.getId(), 30, TimeUnit.MINUTES);
//    }

    // 当键过期时，Redis会自动调用该方法（需要配置Redis的过期事件通知功能）     
    // public void onOrderKeyExpired(String orderId) {         
    //      cancelOrder(orderId);     
    // }
}
