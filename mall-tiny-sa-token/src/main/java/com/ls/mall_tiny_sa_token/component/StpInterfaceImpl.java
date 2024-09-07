package com.ls.mall_tiny_sa_token.component;

import cn.dev33.satoken.stp.StpInterface;
import cn.hutool.core.convert.Convert;
import com.ls.mall_tiny_sa_token.domain.AdminUser;
import com.ls.mall_tiny_sa_token.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @Description: 自定义权限验证接口扩展
 * @Author: ls
 * @Date: 2024/9/7 19:46
 */
@Component
public class StpInterfaceImpl implements StpInterface {
    @Autowired
    private UmsAdminService adminService;
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        AdminUser adminUser = adminService.getAdminById(Convert.toLong(loginId));
        return adminUser.getRole().getPermissionList();
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        AdminUser adminUser = adminService.getAdminById(Convert.toLong(loginId));
        return Collections.singletonList(adminUser.getRole().getName());
    }
}
