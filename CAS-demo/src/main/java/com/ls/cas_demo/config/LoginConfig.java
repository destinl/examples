package com.ls.cas_demo.config;

import com.ls.cas_demo.filter.LoginFilter;
import com.ls.cas_demo.filter.SSOFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/14 22:05
 */
@Configuration
public class LoginConfig {
    @Autowired
    private RedisTemplate redisTemplate;

    //配置filter生效
    @Bean
    public FilterRegistrationBean sessionFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(ssoFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("sessionFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public SSOFilter ssoFilter() {
        return new SSOFilter(redisTemplate);
    }
}
