package com.ls.ssm_exp.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Lombok;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/3017:51
 */
@Data
@AllArgsConstructor
//Lombok 注解来生成无参构造器
@NoArgsConstructor
public class User {
    @NotBlank(message = "Nickname is required.")
    private String nickname;

    @NotNull(message = "age is required.")
    private Integer age;
}
