package com.ls.async_demo.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/1 14:18
 */
@Service
public class AsyncService {
    @Async("taskExecutor")
    public void executeAsyncTask() {
        System.out.println("异步方法开始执行: " + Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("异步方法执行结束: " + Thread.currentThread().getName());
    }

    public Callable<String> executeCallableTask() {
        return () -> {
            System.out.println("开始执行Callable异步任务");
            Thread.sleep(2000);
            return "Callable异步任务执行完成";
        };
    }

    public WebAsyncTask<String> executeWebAsyncTask() {
        Callable<String> callable = () -> {
            System.out.println("开始执行WebAsyncTask异步任务");
            Thread.sleep(2000);
            return "WebAsyncTask异步任务执行完成";
        };
        return new WebAsyncTask<>(3000, callable);
    }

    public DeferredResult<String> executeDeferredResultTask() {
        DeferredResult<String> deferredResult = new DeferredResult<>();
        new Thread(() -> {
            System.out.println("开始执行DeferredResult异步任务");
            try {
                Thread.sleep(2000);
                deferredResult.setResult("DeferredResult异步任务执行完成");
            } catch (InterruptedException e) {
                deferredResult.setErrorResult(e);
            }
        }).start();
        return deferredResult;
    }
}
