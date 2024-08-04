package com.ls.javacgdemo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import nonapi.io.github.classgraph.json.Id;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/4 17:51
 */
@Entity
@Table(name = "t_product")
public class Product {
    @jakarta.persistence.Id
    @Id
    private Integer id;
    private String product_name;
    private Integer product_count;

    public Integer getProductCount() {
        return product_count;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id =  id;
    }

    public void setProductCount(int i) {
        this.product_count = i;
    }

    public void setProductName(String s){
        this.product_name = s;
    }

    public String getProductName() {
        return product_name;
    }


}
