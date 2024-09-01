package com.ls.async_demo.controller;

import com.ls.async_demo.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/1 14:18
 */
@RestController
public class AsyncController {
    @Autowired
    private AsyncService asyncService;
    @GetMapping("/async")
    public String async() {
        System.out.println("请求线程: " + Thread.currentThread().getName());
        asyncService.executeAsyncTask();
        return "异步任务已提交";
    }
}
