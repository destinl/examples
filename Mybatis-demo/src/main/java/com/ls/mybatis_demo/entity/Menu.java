package com.ls.mybatis_demo.entity;

import lombok.AllArgsConstructor;

import java.util.Date;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/2 20:51
 */
@AllArgsConstructor
public class Menu {
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 创建时间
     */
    private Date createTime;

    public Menu(Integer id, String menuName) {
        this.id = id;
        this.menuName = menuName;
    }

// set、get方法等...
}

