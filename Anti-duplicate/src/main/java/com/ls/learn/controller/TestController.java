package com.ls.learn.controller;

import com.ls.learn.annotation.RepeatSubmit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/20 21:39
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @RepeatSubmit(limitType = RepeatSubmit.Type.TOKEN, lockTime = 10)
    @RequestMapping()
    public String saveCountInfo(String accountNo){
        return "ok" + accountNo;
    }
}
