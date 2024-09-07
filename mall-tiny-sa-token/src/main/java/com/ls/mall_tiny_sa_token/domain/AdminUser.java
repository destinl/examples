package com.ls.mall_tiny_sa_token.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/7 18:20
 */
@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class AdminUser {
    private Long id;
    private String username;
    private String password;
    private Long roleId;
    private AdminRole role;
}
