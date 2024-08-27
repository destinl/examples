package com.ls.utils.consumers;

import com.ls.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Description: 延迟队列处理阶段,通过 @RabbitListener(queues = "orderDelayQueue") 注解，该类可以监听到名为 orderDelayQueue 的队列。
 * @Author: ls
 * @Date: 2024/8/27 22:41
 */
//@Component
//@RabbitListener(queues = "orderDelayQueue")
public class OrderDelayConsumer {
//    @Autowired
//    private OrderService orderService;
//    @RabbitHandler
//    public void process(String orderId) {
        // 取消订单
        // orderService.cancelOrder(orderId);
//    }
}
