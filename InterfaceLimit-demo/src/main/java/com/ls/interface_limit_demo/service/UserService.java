package com.ls.interface_limit_demo.service;

import com.ls.interface_limit_demo.domain.entity.AddReq;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/3 22:28
 */
@Service
public class UserService {

    public ResponseEntity<String> add(AddReq addReq) {
        return ResponseEntity.ok("ok");
    }
}
