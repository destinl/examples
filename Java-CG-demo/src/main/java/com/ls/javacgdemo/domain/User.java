package com.ls.javacgdemo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import nonapi.io.github.classgraph.json.Id;

import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/28 14:29
 */

//@Entity
//@Table(name = "t_user")
public class User {

//    @jakarta.persistence.Id
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    // 此处仅映射明文字段，密文字段由ShardingSphere处理
    private String passwordPlain;

    private List<String> tags;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordPlain() {
        return passwordPlain;
    }

    public void setPasswordPlain(String passwordPlain) {
        this.passwordPlain = passwordPlain;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public List<String> getTags() {
        return tags;
    }
    // getter and setter methods
}
