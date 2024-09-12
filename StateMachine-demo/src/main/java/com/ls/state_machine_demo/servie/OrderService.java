package com.ls.state_machine_demo.servie;

//import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ls.state_machine_demo.annotation.LogResult;
import com.ls.state_machine_demo.domain.constant.CommonConstants;
import com.ls.state_machine_demo.domain.entity.Order;
import com.ls.state_machine_demo.enums.OrderStatus;
import com.ls.state_machine_demo.enums.OrderStatusChangeEvent;
import com.ls.state_machine_demo.mapper.OrderMapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.annotation.OnTransition;
import org.springframework.statemachine.persist.StateMachinePersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/11 23:11
 */
@Service("orderService")
@Slf4j
public class OrderService {
    @Resource
    private StateMachine<OrderStatus, OrderStatusChangeEvent> orderStateMachine;
    @Resource
    private StateMachinePersister<OrderStatus, OrderStatusChangeEvent, String> stateMachineMemPersister;
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 创建订单
     *
     * @param order
     * @return
     */
    public Order create(Order order) {
        order.setStatus(OrderStatus.WAIT_PAYMENT.getKey());
        orderMapper.insert(order);
        return order;
    }

    /**
     * 对订单进行支付
     *
     * @param id
     * @return
     */
    public Order pay(Long id) {
        Order order = orderMapper.selectById(id);
        log.info("线程名称：{},尝试支付，订单号：{}" ,Thread.currentThread().getName() , id);
        //sendEvent(OrderStatusChangeEvent.PAYED, order,CommonConstants.payTransition)
        if (!sendEvent(OrderStatusChangeEvent.PAYED, order)) {
            log.error("线程名称：{},支付失败, 状态异常，订单信息：{}", Thread.currentThread().getName(), order);
            throw new RuntimeException("支付失败, 订单状态异常");
        }
        return order;
    }
    /**
     * 对订单进行发货
     *
     * @param id
     * @return
     */
    public Order deliver(Long id) {
        Order order = orderMapper.selectById(id);
        log.info("线程名称：{},尝试发货，订单号：{}", Thread.currentThread().getName(), id);
        if (!sendEvent(OrderStatusChangeEvent.DELIVERY, order)) {
            log.error("线程名称：{},发货失败, 状态异常，订单信息：{}", Thread.currentThread().getName(), order);
            throw new RuntimeException("发货失败, 订单状态异常");
        }
        return order;
    }

    /**
     * 对订单进行确认收货
     *
     * @param id
     * @return
     */
    public Order receive(Long id) {
        Order order = orderMapper.selectById(id);
        log.info("线程名称：{},尝试收货，订单号：{}", Thread.currentThread().getName(), id);
        if (!sendEvent(OrderStatusChangeEvent.RECEIVED, order)) {
            log.error("线程名称：{},收货失败, 状态异常，订单信息：{}", Thread.currentThread().getName(), order);
            throw new RuntimeException("收货失败, 订单状态异常");
        }
        return order;
    }
    /**
     * 发送订单状态转换事件
     * synchronized修饰保证这个方法是线程安全的
     *
     * @param changeEvent
     * @param order
     * @return
     */
    //发送事件改造：如果获取到业务执行异常，则返回失败，不进行状态机持久化
    private synchronized boolean sendEvent(OrderStatusChangeEvent changeEvent, Order order) {
        boolean result = false;
        try {
            //启动状态机
            orderStateMachine.start();
            //尝试恢复状态机状态
            stateMachineMemPersister.restore(orderStateMachine, String.valueOf(order.getId()));
            Message message = MessageBuilder.withPayload(changeEvent).setHeader("order", order).build();
            result = orderStateMachine.sendEvent(message);
            if (!result) {
                return false;
            }
            //获取到监听的结果信息
            Integer o = (Integer) orderStateMachine.getExtendedState().getVariables().get(CommonConstants.payTransition + order.getId());
            //操作完成之后,删除本次对应的key信息
            orderStateMachine.getExtendedState().getVariables().remove(CommonConstants.payTransition + order.getId());
            //如果事务执行成功，则持久化状态机
            if (Objects.equals(1, Integer.valueOf(o))) {
                //持久化状态机状态
                stateMachineMemPersister.persist(orderStateMachine, String.valueOf(order.getId()));
            } else {
                //订单执行业务异常
                return false;
            }
        } catch (Exception e) {
            log.error("订单操作失败:{}", e);
        } finally {
            orderStateMachine.stop();
        }
        return result;
    }

    public Order selectById(Long id) {
        return orderMapper.selectById(id);
    }

    //从orderStateMachine.sendEvent(message);获取的结果无法感知到。无论执行正常还是抛出异常，都返回true。
    @OnTransition(source = "WAIT_PAYMENT", target = "WAIT_DELIVER")
    @Transactional(rollbackFor = Exception.class)
    @LogResult(key = CommonConstants.payTransition)
    public void payTransition(Message<OrderStatusChangeEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        log.info("支付，状态机反馈信息：{}", message.getHeaders().toString());
        try {
            //更新订单
            order.setStatus(OrderStatus.WAIT_DELIVER.getKey());
            orderMapper.updateById(order);
            //TODO 其他业务
            //模拟异常
            if (Objects.equals(order.getName(), "A")) {
                throw new RuntimeException("执行业务异常");
            }
        } catch (Exception e) {
            //如果出现异常，记录异常信息，抛出异常信息进行回滚
            log.error("payTransition 出现异常：{}", e);
            throw e;
        }
    }

    @OnTransition(source = "WAIT_DELIVER", target = "WAIT_RECEIVE")
    @LogResult(key = CommonConstants.deliverTransition)
    public void deliverTransition(Message<OrderStatusChangeEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        log.info("发货，状态机反馈信息：{}", message.getHeaders().toString());
        //更新订单
        order.setStatus(OrderStatus.WAIT_RECEIVE.getKey());
        orderMapper.updateById(order);
        //TODO 其他业务
    }
    @OnTransition(source = "WAIT_RECEIVE", target = "FINISH")
    @LogResult(key = CommonConstants.receiveTransition)
    public void receiveTransition(Message<OrderStatusChangeEvent> message) {
        Order order = (Order) message.getHeaders().get("order");
        log.info("确认收货，状态机反馈信息：{}", message.getHeaders().toString());
        //更新订单
        order.setStatus(OrderStatus.FINISH.getKey());
        orderMapper.updateById(order);
        //TODO 其他业务
    }

    //改造监听状态：把业务的执行结果进行保存，1成功，0失败
//    @OnTransition(source = "WAIT_PAYMENT", target = "WAIT_DELIVER")
//    @Transactional(rollbackFor = Exception.class)
//    public void payTransition(Message<OrderStatusChangeEvent> message) {
//        Order order = (Order) message.getHeaders().get("order");
//        log.info("支付，状态机反馈信息：{}",  message.getHeaders().toString());
//        try {
//            //更新订单
//            order.setStatus(OrderStatus.WAIT_DELIVER.getKey());
//            orderMapper.updateById(order);
//            //TODO 其他业务
//            //模拟异常
//            if(Objects.equals(order.getName(),"A")){
//                throw new RuntimeException("执行业务异常");
//            }
//            //成功 则为1
//            orderStateMachine.getExtendedState().getVariables().put(CommonConstants.payTransition+order.getId(),1);
//        } catch (Exception e) {
//            //如果出现异常，则进行回滚
//            log.error("payTransition 出现异常：{}",e);
//            //将异常信息变量信息中，失败则为0
//            orderStateMachine.getExtendedState().getVariables().put(CommonConstants.payTransition+order.getId(), 0);
//            throw e;
//        }
//    }
}

