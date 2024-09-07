package com.ls.mall_tiny_sa_token.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: Sa-Token相关配置
 * @Author: ls
 * @Date: 2024/9/7 19:40
 */
@Configuration
public class SaTokenConfig implements WebMvcConfigurer {

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    /**
     * 注册sa-token拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new SaInterceptor((handler) -> {
            SaRouter.match("/**", StpUtil::checkLogin);
            // 角色认证：ROLE_ADMIN可以访问所有接口，ROLE_USER只能访问查询全部接口
//            SaRouter.match("/brand/listAll", () -> {
//                StpUtil.checkRoleOr("ROLE_ADMIN","ROLE_USER");
//                //强制退出匹配链
//                SaRouter.stop();
//            });
//            SaRouter.match("/brand/**", () -> StpUtil.checkRole("ROLE_ADMIN"));
            // 权限认证：不同接口, 校验不同权限
            SaRouter.match("/brand/listAll", () -> StpUtil.checkPermission("brand:read"));
            SaRouter.match("/brand/create", () -> StpUtil.checkPermission("brand:create"));
            SaRouter.match("/brand/update/{id}", () -> StpUtil.checkPermission("brand:update"));
            SaRouter.match("/brand/delete/{id}", () -> StpUtil.checkPermission("brand:delete"));
            SaRouter.match("/brand/list", () -> StpUtil.checkPermission("brand:read"));
            SaRouter.match("/brand/{id}", () -> StpUtil.checkPermission("brand:read"));
        })).addPathPatterns("/**").excludePathPatterns(ignoreUrlsConfig.getUrls());
    }

    // Sa-Token 整合 jwt (Simple 简单模式)
    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForSimple();
    }
}
