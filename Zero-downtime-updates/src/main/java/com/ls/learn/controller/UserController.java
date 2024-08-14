package com.ls.learn.controller;

import com.ls.learn.annotation.Monitor;
import com.ls.learn.domain.vo.User;
import com.ls.learn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/14 22:46
 */
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @Monitor(tags = {"UserController", "list"})
    @GetMapping("")
    public List<User> list() {
        return this.userService.queryUsers() ;
    }

    @Monitor(tags = {"UserController", "ById"})
    @GetMapping("/{id}")
    public User queryById(@PathVariable(name="id", required = true) Long id) {
        return this.userService.queryById(id) ;
    }
}
