package com.ls.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/10 15:55
 */
@Component
public class TimeCountListener implements ApplicationListener<ServletRequestHandledEvent> {

    @Override
    public void onApplicationEvent(ServletRequestHandledEvent event) {
        Throwable failureCause = event.getFailureCause() ;
        if (failureCause != null) {
            System.err.printf("错误原因: %s%n", failureCause.getMessage()) ;
        }
        System.err.println("========================================") ;
        System.err.printf("请求客户端地址：%s\n请求URL: %s\n请求Method: %s\n请求耗时: %d毫秒%n",
                event.getClientAddress(),
                event.getRequestUrl(),
                event.getMethod(),
                event.getProcessingTimeMillis());
        System.err.println("========================================") ;
    }
}
