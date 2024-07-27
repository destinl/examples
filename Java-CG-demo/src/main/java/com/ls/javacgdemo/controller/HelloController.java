package com.ls.javacgdemo.controller;

//import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/27 18:04
 */
@RestController
@RequestMapping("/api")
//@Api(tags = "后台管理接口")
//@Tag(name = "body参数")
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<String> sayHello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return ResponseEntity.ok("Hello, " + name);
    }
}
