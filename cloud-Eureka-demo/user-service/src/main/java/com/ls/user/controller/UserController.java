package com.ls.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/21 15:15
 */

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
//    @Autowired
//    private UserService userService;
    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public String queryById(@PathVariable("id") Long id) {
//        return userService.getById(id);
        return "hello" + id;
    }
}
