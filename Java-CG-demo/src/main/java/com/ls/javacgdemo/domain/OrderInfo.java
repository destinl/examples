package com.ls.javacgdemo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/4 17:55
 */
@Entity
@Table(name = "t_order_info")
public class OrderInfo {
    @Id
    @GeneratedValue
    private Integer id;
    private String buyName;
    private String buyGood;
    public void setBuyName(String name) {
        this.buyName = name;
    }

    public void setBuyGoods(String productName) {
        this.buyGood = productName;
    }

    public String getBuyName() {
        return buyName;
    }

    public String getBuyGoods() {
        return buyGood;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
