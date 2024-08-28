package com.ls.config;

import com.ls.filter.CrossFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/28 20:48
 */
@Configuration
public class FilterConfig {
    /**
     * 添加CrossFilter过滤器
     * @return
     */
//    @Bean
//    public FilterRegistrationBean crossFilterBean() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setName("crossFilter"); // 指定过滤器名称
//        registration.setFilter(new CrossFilter()); // 指定过滤器实现类
//        registration.setUrlPatterns(Collections.singleton("/*"));// 指定拦截路径
//        registration.setOrder(1);// 指定顺序
//        return registration;
//    }
}