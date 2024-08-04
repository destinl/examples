package com.ls.javacgdemo.controller;

import com.ls.javacgdemo.domain.User;
import com.ls.javacgdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.CountDownLatch;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/28 14:32
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.saveUser(user);
        return ResponseEntity.ok(savedUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> user = userService.findById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(path = "sell", method = RequestMethod.GET)
    public void sell(){
        CountDownLatch countDownLatch = new CountDownLatch(100);
        for(int i = 0; i < 100; i++){
            new Thread(()->{
                try{
                    countDownLatch.await();
                    userService.lockPoduct();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
            countDownLatch.countDown();
        }
    }
}
