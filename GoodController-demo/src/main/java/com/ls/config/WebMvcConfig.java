package com.ls.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 手动添加资源映射，前端就能正常访问静态资源了（在前后端不分离项目中可以这样用，防止/static目录下的静态资源前端无法正常访问）
 * @Author: ls
 * @Date: 2024/8/12 21:05
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 允许访问localhost:8080/static/目录下的静态资源
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
