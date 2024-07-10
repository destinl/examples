package com.ls.ssm_exp.config;

import com.ls.ssm_exp.Filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.annotation.WebFilter;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1015:58
 */
@Configuration
public class FilterConfig {
    //    注册过滤器（可选，如果不使用 @WebFilter 注解）
    @Bean
    public FilterRegistrationBean<MyFilter> loggingFilter(){
        FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<>();

        // 注册自定义过滤器实例
        registrationBean.setFilter(new MyFilter());

        // 指定过滤器应用的 URL 模式
        registrationBean.addUrlPatterns("/*");

        // 设置过滤器优先级，值越低优先级越高
        registrationBean.setOrder(1);

        return registrationBean;
    }
}
