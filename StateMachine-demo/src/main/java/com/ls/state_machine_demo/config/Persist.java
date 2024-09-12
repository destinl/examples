package com.ls.state_machine_demo.config;

import com.alibaba.fastjson2.JSON;
import com.ls.state_machine_demo.domain.entity.Order;
import com.ls.state_machine_demo.enums.OrderStatus;
import com.ls.state_machine_demo.enums.OrderStatusChangeEvent;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachineContext;
import org.springframework.statemachine.StateMachinePersist;
import org.springframework.statemachine.persist.DefaultStateMachinePersister;
import org.springframework.statemachine.persist.RepositoryStateMachinePersist;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.statemachine.redis.RedisStateMachineContextRepository;
import org.springframework.statemachine.redis.RedisStateMachinePersister;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 配置持久化
 * @Author: ls
 * @Date: 2024/9/11 23:07
 */
@Configuration
@Slf4j
public class Persist<E, S> {
    /**
     * 持久化到内存map中
     *
     * @return
     */
    @Bean(name = "stateMachineMemPersister")
    public static StateMachinePersister getPersister() {
        return new DefaultStateMachinePersister(new StateMachinePersist() {
            @Override
            public void write(StateMachineContext context, Object contextObj) throws Exception {
                log.info("持久化状态机,context:{},contextObj:{}", JSON.toJSONString(context), JSON.toJSONString(contextObj));
                map.put(contextObj, context);
            }
            @Override
            public StateMachineContext read(Object contextObj) throws Exception {
                log.info("获取状态机,contextObj:{}", JSON.toJSONString(contextObj));
                StateMachineContext stateMachineContext = (StateMachineContext) map.get(contextObj);
                log.info("获取状态机结果,stateMachineContext:{}", JSON.toJSONString(stateMachineContext));
                return stateMachineContext;
            }
            private Map map = new HashMap();
        });
    }
    @Resource
    private RedisConnectionFactory redisConnectionFactory;
    /**
     * 持久化到redis中，在分布式系统中使用
     *
     * @return
     */
    @Bean(name = "stateMachineRedisPersister")
    public RedisStateMachinePersister<E, S> getRedisPersister() {
        RedisStateMachineContextRepository<E, S> repository = new RedisStateMachineContextRepository<>(redisConnectionFactory);
        RepositoryStateMachinePersist p = new RepositoryStateMachinePersist<>(repository);
        return new RedisStateMachinePersister<>(p);
    }

//    @Resource
//    private StateMachinePersister<OrderStatus, OrderStatusChangeEvent, String> stateMachineRedisPersister;
//    /**
//     * 发送订单状态转换事件
//     * synchronized修饰保证这个方法是线程安全的
//     *
//     * @param changeEvent
//     * @param order
//     * @return
//     */
//    private synchronized boolean sendEvent(OrderStatusChangeEvent changeEvent, Order order) {
//        boolean result = false;
//        try {
//            //启动状态机
//            orderStateMachine.start();
//            //尝试恢复状态机状态
//            stateMachineRedisPersister.restore(orderStateMachine, String.valueOf(order.getId()));
//            Message message = MessageBuilder.withPayload(changeEvent).setHeader("order", order).build();
//            result = orderStateMachine.sendEvent(message);
//            //持久化状态机状态
//            stateMachineRedisPersister.persist(orderStateMachine, String.valueOf(order.getId()));
//        } catch (Exception e) {
//            log.error("订单操作失败:{}", e);
//        } finally {
//            orderStateMachine.stop();
//        }
//        return result;
//    }


}
