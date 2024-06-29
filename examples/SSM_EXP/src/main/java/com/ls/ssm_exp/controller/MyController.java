package com.ls.ssm_exp.controller;

import com.ls.ssm_exp.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/2912:21
 */
@RestController
@RequestMapping("/")
public class MyController {

    @Autowired
    private MyService myService; // 假设您的服务类名为 MyService

    @GetMapping("test")
    public void someTransactionalOperation(String name){
        myService.someTransactionalOperation("Transactional");
    }
}
