//package com.ls.request_body_advice_demo.config;
//
//import com.ls.request_body_advice_demo.resolver.UserArgumentResolver;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.method.support.HandlerMethodArgumentResolver;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
//
//import java.util.List;
//
///**
// * @Description:
// * @Author: ls
// * @Date: 2024/8/31 23:15
// */
//@Configuration
//public class WebConfig  extends WebMvcConfigurerAdapter {
//    @Autowired
//    UserArgumentResolver userArgumentResolver;
//    @Override
//    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
//        argumentResolvers.add(userArgumentResolver);
//    }
//}