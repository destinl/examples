package com.ls.learn.config;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/18 16:23
 */

@Component
public class ResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置本地一个目录，以/docs开头进行访问里面的文件
        registry
                .addResourceHandler("/docs/**")
                .addResourceLocations("file:///D:/Chromedownloads/documents/") ;
    }
}