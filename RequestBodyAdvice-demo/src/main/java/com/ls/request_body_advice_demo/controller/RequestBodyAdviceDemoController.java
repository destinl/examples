package com.ls.request_body_advice_demo.controller;

import com.ls.request_body_advice_demo.annotation.DecodeBody;
//import com.ls.request_body_advice_demo.annotation.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/31 22:51
 */
@RestController
@RequestMapping
public class RequestBodyAdviceDemoController {
    @PostMapping("/demo")
    public ResponseEntity<String> demo (@RequestBody @DecodeBody String payload) {
        return ResponseEntity.ok(payload);
    }


    @GetMapping("/test/{user}")
    public ResponseEntity<String> list(@PathVariable String user) {
        System.out.println(user);
        return ResponseEntity.ok(user);
    }//@UserInfo
}

