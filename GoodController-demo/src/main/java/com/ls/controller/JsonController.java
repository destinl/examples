package com.ls.controller;

import com.ls.domain.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/26 22:29
 */
@RestController
@RequestMapping("/json")
public class JsonController {
    @GetMapping("/jsonanygetter")
    public User jsonanygetter() {
        User user = new User("张三", Map.of("key1", "value1", "key2", "value2"));
        user.setJson("""
                {
                        "age": 22,        "birhday": "1999-12-12"      
                }
                """) ;
        return user;
    }
}
