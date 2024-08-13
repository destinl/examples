//package com.ls.learn.websocket;
//
//import org.apache.logging.log4j.message.Message;
//import org.springframework.stereotype.Component;
//
///**
// * @Description: 所有Websocket Server 接收消息并处理
// * @Author: ls
// * @Date: 2024/8/13 21:43
// */
//@Component
//@RequiredArgsConstructor
//public class CreateOrderConsumer implements BaseConsumer {
//
//
//    private final WebSocketServer0 webSocketServer0;
//
//
//    @Override
//    public Action handleMessage(Message message) {
//        CreateOrderMessage createOrderMessage = JSON.parseObject(message.getBody(), LinkCreateOrderMessage.class);
//
//        try {
//            //业务校验省略...
//            //调用WebSocketServer的sendMessageToOneUser方法，里面根据客户端userid找到对应session， 只有存在userid和session的绑定关系的Websocket Server才发送消息到客户端
//            webSocketServer0.sendMessageToOneUser(createOrderMessage.getUserId(), JSON.toJSONString(linkActionRes),
//                    message.getMsgID());
//        } catch (Exception e) {
//            e.printStackTrace();
//            return Action.ReconsumeLater;
//        }
//        return Action.CommitMessage;
//    }
//}
