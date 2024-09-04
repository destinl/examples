package com.ls.interface_limit_demo.controller;

import com.ls.interface_limit_demo.annotation.RequestLock;
import com.ls.interface_limit_demo.domain.entity.AddReq;
import com.ls.interface_limit_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/3 22:26
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping("/add")
    @RequestLock(prefix = "test")
//    @RequiresPermissions(value = "add")
//    @Log(methodDesc = "添加用户")
    public ResponseEntity<String> add(@RequestBody AddReq addReq) {
        return userService.add(addReq);
    }
}
