package com.ls.ssm_exp.domain.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/3017:51
 */
@Data
public class User {
    @NotBlank(message = "Nickname is required.")
    private String nickname;

    private Integer age;
}
