package com.ls.webflux_demo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/2 21:45
 */
@Data
@Document
public class User {
    @Id
    private String id;
    private String name;
    private int age;
    // Getters and Setters
}
