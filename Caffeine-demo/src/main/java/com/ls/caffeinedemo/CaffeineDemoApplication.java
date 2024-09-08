package com.ls.caffeinedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RouterFunctions.route;

@EnableCaching
@SpringBootApplication
public class CaffeineDemoApplication {

//    @Bean
//    public RouterFunction<ServerResponse> routeFuction(){
//        return route()
//                .GET("/getUserName", request -> ServerResponse.ok().body("zhouyu"))
//                .GET("/getUserAge", request -> ServerResponse.ok().body("88"))
//                .build();
//    }


    public static void main(String[] args) {
        SpringApplication.run(CaffeineDemoApplication.class, args);
    }

}
