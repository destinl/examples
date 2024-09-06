package com.ls.jasypt_demo.controller;

import com.ls.jasypt_demo.domain.entity.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/6 23:00
 */
@RestController
public class TestController {
    @GetMapping("/test")
    public Person test(){
        Person user = new Person();
        user.setRealName("小刘同学");
        user.setPhoneNumber(" 15129390011");
        user.setAddress("陕西省西安市雁塔区....");
        user.setIdCard("4333333333334334333");
        return user;
    }
}
