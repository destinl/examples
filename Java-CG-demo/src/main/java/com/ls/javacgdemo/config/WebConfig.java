package com.ls.javacgdemo.config;

import com.ls.javacgdemo.interceptor.IpWhitelistInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/5 21:23
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private IpWhitelistInterceptor ipWhitelistInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(ipWhitelistInterceptor)
                .addPathPatterns("/**"); // 这里可以根据需要指定拦截的路径
    }
}