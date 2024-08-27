package com.ls.config;

import com.ls.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/27 22:50
 */
//@Configuration
public class RedisConfig {

//    @Autowired
//    private RedisConnectionFactory redisConnectionFactory;
//
//    @Autowired
//    private OrderService orderService;
//
//    @Bean
//    public RedisMessageListenerContainer container() {
//        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
//        container.setConnectionFactory(redisConnectionFactory);
//        container.addMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(byte[] message, byte[] pattern) {
//                String expiredKey = new String(message);
//                if (expiredKey.startsWith("order:")) {
//                    String orderId = expiredKey.split(":")[1];
//                    orderService.cancelOrder(orderId);
//                }
//            }
//        }, new ChannelTopic("__keyevent@0__:expired"));
//        return container;
//    }

//    container() 方法创建并配置了一个 RedisMessageListenerContainer，
//    该容器用于管理 Redis 的消息监听器。
//
//            container.setConnectionFactory(redisConnectionFactory);
//    设置 Redis 连接工厂，确保容器可以与 Redis 服务器建立连接。
//
//            container.addMessageListener(...)
//    向容器添加了一个消息监听器，这个监听器负责处理特定的 Redis 键过期事件。
//
//            new MessageListener() {...}
//    创建了一个匿名内部类，实现了 MessageListener 接口，覆盖了 onMessage 方法。
//
//    在 onMessage 方法中实现了处理键过期事件的逻辑，
//    每当有 Redis 键过期时，onMessage 方法会被自动调用。
//
//            message.toString() 获取过期的键名，
//    然后检查键名是否以 order: 开头，以确定是订单超时的键。
//
//            expiredKey.split(":")[1];
//    通过 split(":") 分割过期键名，获取 orderId，用于后续调用 orderService.cancelOrder(orderId); 方法。


}
