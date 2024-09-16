package com.ls.typehandler_demo.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/16 12:14
 */

public class Address {
    private String street;
    private String city;

    // getters and setters
    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
