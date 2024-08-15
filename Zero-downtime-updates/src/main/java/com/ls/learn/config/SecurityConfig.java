//package com.ls.learn.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
///**
// * @Description:
// * @Author: ls
// * @Date: 2024/8/15 20:27
// */
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
////        http
////                .authorizeRequests(authorizeRequests ->
////                        authorizeRequests
////                                .requestMatchers("/login", "/oauth2/**").permitAll()
////                                .anyRequest().authenticated()
////                );
////                .oauth2Login(oauth2Login ->
////                        oauth2Login
////                                .loginPage("/login")
////                );
//        return http.build();
//    }
//}