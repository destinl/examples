package com.ls.request_body_advice_demo.controller;

import com.ls.request_body_advice_demo.annotation.DecodeBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
