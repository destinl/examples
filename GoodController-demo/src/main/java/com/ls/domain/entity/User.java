package com.ls.domain.entity;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Random;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/26 22:27
 */
@Getter
@Setter
@AllArgsConstructor
//@JsonIncludeProperties({"name", "properties"})
public class User {
    private String name;
    private Map<String, String> properties;
    @JsonRawValue
    private String json ;

    @JsonAnyGetter
    Map<String, String> getProperties() {
        return properties;
    }

    public User(String name, Map<String, String> properties) {
        this.name = name;
        this.properties = properties;
    }

    @JsonGetter
    public String info() {
        return "info - " + new Random().nextInt(10000) ;
    }
}
