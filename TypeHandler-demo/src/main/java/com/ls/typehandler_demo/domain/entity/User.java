package com.ls.typehandler_demo.domain.entity;


/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/16 12:15
 */
public class User {
    private int id;
    private String name;
    private Address address;

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
