package com.ls.mall_tiny_sa_token.service;

import cn.dev33.satoken.stp.SaTokenInfo;
import com.ls.mall_tiny_sa_token.domain.AdminUser;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/7 18:06
 */
public interface UmsAdminService {
    /**
     * 根据用户名获取用户信息
     */
    AdminUser getAdminByUsername(String username);

    /**
     * 根据用户ID获取用户
     */
    AdminUser getAdminById(Long id);

    /**
     * 用户名密码登录
     */
    SaTokenInfo login(String username, String password);
}
