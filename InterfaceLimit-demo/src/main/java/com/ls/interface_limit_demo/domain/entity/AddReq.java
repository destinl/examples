package com.ls.interface_limit_demo.domain.entity;

import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/3 22:30
 */
public class AddReq {
    /**     * 用户名称     */    private String userName;
    /**     * 用户手机号     */    private String userPhone;
    /**     * 角色ID列表     */    private List<Long> roleIdList;
}
