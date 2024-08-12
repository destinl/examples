package com.ls.learn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/12 21:32
 */
@RestController()
@RequestMapping("port/test")
public class TestPortController {
    @GetMapping("test")
    public String test() {
        return "1";
    }
}
