package com.ls.jwt_demo.domain.dto;

import lombok.Data;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/15 23:14
 */
@Data
public class UserDto {
    private String userEmail;
    private String userPwd;
}
