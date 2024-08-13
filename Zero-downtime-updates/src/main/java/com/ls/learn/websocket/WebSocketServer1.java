//package com.ls.learn.websocket;
//
//import io.micrometer.common.util.StringUtils;
//import jakarta.annotation.Resource;
//import jakarta.websocket.EndpointConfig;
//import jakarta.websocket.OnClose;
//import jakarta.websocket.OnError;
//import jakarta.websocket.OnOpen;
//import jakarta.websocket.server.PathParam;
//import jakarta.websocket.server.ServerEndpoint;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.ConcurrentHashMap;
//
///**
// * @Description:
// * @Author: ls
// * @Date: 2024/8/13 21:52
// */
//@ServerEndpoint("/websocket/{businessType}/{userId}")
//@Component
//public class WebSocketServer1 {
//    /**
//     * 若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
//     * 注意：allSession 只记录当前机器的 客户端连接，不是所有session连接
//     */
//
//    public static ConcurrentHashMap<String, Session> allSession = new ConcurrentHashMap<>();
//    /**
//     *
//     */
//    private String myApplicationName = System.getProperty("myApplicationName");
//    @Resource
//    private RedisService redisService;
//
//    /**
//     * 连接建立成功调用的方法
//     * 关键代码
//     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
//     */
//    @OnOpen
//    public void onOpen(@PathParam(value = "businessType") String businessType, @PathParam(value = "userId") String userId, Session session, EndpointConfig config) {
//        if (StringUtils.isEmpty(userId)) {
//            return;
//        }
//        /**
//         * 加入到本地map
//         */
//        allSession.put(userId, session);
//        //绑定userid和服务名唯一Id的关系
//        redisService.hset(WS_MAPPING, userId + "", myApplicationName);
//    }
//
//    /**
//     * 连接关闭调用的方法
//     */
//    @OnClose
//    public void onClose(@PathParam(value = "userId") String userId, Session session) {
//        if (StringUtils.isNotEmpty(userId)) {
//            allSession.remove(userId);
//        }
//    }
//
//    /**
//     * 发生错误时调用
//     *
//     * @param
//     * @param
//     */
//    @OnError
//    public void onError(@PathParam(value = "userId") String userId, Session session, Throwable error) {
//    }
//
//    /**
//     * 用户id
//     *
//     * @param userId
//     * @param message
//     */
//    public void sendMessageToOneUser(Integer userId, String message) {
//        if (userId == null) {
//            return;
//        }
//        Session session = allSession.get(String.valueOf(userId));
//        if (session != null) {
//            //所有Websocket Server 根据客户端userid找到对应session， 只有存在userid和session的绑定关系的Websocket Server才发送消息到客户端
//            session.getAsyncRemote().sendText(message);
//        } else {
//            System.err.println("session为空");
//            allSession.remove(userId + "");
//        }
//    }
//}
