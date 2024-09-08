package com.ls.caffeinedemo.controller;

import com.ls.caffeinedemo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.RouterFunctions;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.web.servlet.function.RequestPredicates.GET;
import static org.springframework.web.servlet.function.RequestPredicates.accept;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/8 22:41
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @Bean
    public RouterFunction<ServerResponse> route() {
        return RouterFunctions
                .route(GET("/users/{userId}").and(accept(MediaType.TEXT_PLAIN)),
                        request -> ServerResponse.ok()
                                .contentType(MediaType.TEXT_PLAIN)
                                .body(BodyInserters.fromValue(userService.getUserById(request.pathVariable("userId")))));
    }
}
