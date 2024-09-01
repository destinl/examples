package com.ls.async_demo.config.exception;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.lang.reflect.Method;

/**
 * @Description: 异常处理:在异步方法中处理异常与同步方法不同。异步方法的异常不会直接抛出到调用者，而是需要通过 AsyncUncaughtExceptionHandler 处理。
 * @Author: ls
 * @Date: 2024/9/1 14:21
 */
@Configuration
public class AsyncExceptionHandler implements AsyncConfigurer {
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new CustomAsyncExceptionHandler();
    }
    class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        @Override
        public void handleUncaughtException(Throwable throwable, Method method, Object... objects) {
            System.err.println("异步方法异常: " + throwable.getMessage());
            System.err.println("方法: " + method.getName());
        }
    }
}