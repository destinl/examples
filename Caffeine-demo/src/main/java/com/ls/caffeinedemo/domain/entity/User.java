package com.ls.caffeinedemo.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/8 22:38
 */
@Data
@AllArgsConstructor
public class User {
    private String userId;
    private String userName;
}
