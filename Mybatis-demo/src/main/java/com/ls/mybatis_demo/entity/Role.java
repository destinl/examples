package com.ls.mybatis_demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/2 20:36
 */
@AllArgsConstructor
@Data
public class Role {
    /**
     * 主键ID
     */
    private Integer id;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 创建时间
     */
    private Date createTime;

    public Role(Integer id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }
    // set、get方法等...
}
