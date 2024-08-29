package com.ls.loginlimit.controller;

import com.ls.loginlimit.annotation.LimitCount;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/29 21:13
 */
@Slf4j
@RestController
public class LoginController {
    @GetMapping("/login")
    @LimitCount(key = "login", name = "登录接口", prefix = "limit")
    public String login(
            @RequestParam(required = true) String username,
            @RequestParam(required = true) String password, HttpServletRequest request) throws Exception {
        if (StringUtils.equals("张三", username) && StringUtils.equals("123456", password)) {
            return "登录成功";
        }
        return "账户名或密码错误";
    }
}
