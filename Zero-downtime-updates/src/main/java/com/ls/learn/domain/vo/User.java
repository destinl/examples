package com.ls.learn.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/14 22:45
 */
@Data
@AllArgsConstructor
public class User {
    private Long id;
    private String name;
    private String gender;
    private int age;
}
