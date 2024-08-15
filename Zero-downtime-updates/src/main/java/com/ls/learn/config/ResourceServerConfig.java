//package com.ls.learn.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
//import org.springframework.security.web.SecurityFilterChain;
//
///**
// * @Description:
// * @Author: ls
// * @Date: 2024/8/15 20:31
// */
//@Configuration
//@EnableWebSecurity
//public class ResourceServerConfig {
//
//    @Bean
//    public SecurityFilterChain resourceServerSecurityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/api/**").authenticated()
//                                .anyRequest().permitAll()
//                )
//                .oauth2ResourceServer(oauth2 ->
//                        oauth2.jwt(jwt ->
//                                jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())
//                        )
//                );
//        return http.build();
//    }
//
//    private JwtAuthenticationConverter jwtAuthenticationConverter() {
//        return new JwtAuthenticationConverter();
//    }
//}
