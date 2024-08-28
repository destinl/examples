package com.ls.learn.controller;

import com.ls.learn.domain.vo.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/28 21:00
 */
@RestController
public class Test8080to8848Controller {
    @GetMapping("/query8848")
    public String list() {
        return "hello, I'm 8848";
    }
}
