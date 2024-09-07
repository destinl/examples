package com.ls.mall_tiny_sa_token.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/7 18:20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
public class AdminRole {
    private Long id;
    private String name;
    private List<String> permissionList;
}