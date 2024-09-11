package com.ls.state_machine_demo.enums;

/**
 * @Description: 事件
 * @Author: ls
 * @Date: 2024/9/11 23:03
 */
public enum OrderStatusChangeEvent {
    // 支付，发货，确认收货
    PAYED,
    DELIVERY,
    RECEIVED;
}
