package com.ls.ssm_exp.config;

import com.ls.ssm_exp.Interceptor.MyInterceptor;
import com.ls.ssm_exp.Interceptor.RateLimitInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1015:38
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    //注册拦截器
    private final RateLimitInterceptor rateLimitInterceptor;

    @Autowired
    public WebMvcConfig(RateLimitInterceptor rateLimitInterceptor) {
        this.rateLimitInterceptor = rateLimitInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimitInterceptor)
                .addPathPatterns("/**") // 对所有请求进行拦截
                .excludePathPatterns("/exclude/**"); // 排除路径

        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/register"); // 可以排除某些路径不被拦截
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://allowed-origin.com")
                .allowedMethods("GET", "POST")
                .allowedHeaders("header1", "header2")
                .exposedHeaders("header3")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
