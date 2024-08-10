package com.ls.sentinel_demo.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/10 15:32
 */
@RequestMapping("/hello")
@RestController
public class HelloController {

    @GetMapping(value = "/sayHi")
    @SentinelResource("sayHi")
    public String sayHi() {
        return "Hello, Sentinel!";
    }
}
