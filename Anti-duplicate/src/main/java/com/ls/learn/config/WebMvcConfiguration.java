package com.ls.learn.config;

import com.ls.learn.interceptor.EscapeHtmlRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/24 17:03
 */
@Component
public class WebMvcConfiguration implements WebMvcConfigurer {
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor((HandlerInterceptor) new EscapeHtmlRequestInterceptor()).addPathPatterns("/**");
    }
}
