package com.ls.webflux_demo.router;

import com.ls.webflux_demo.handler.UserHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/2 21:50
 */
@Configuration
public class RouterConfig {
    @Bean
    public RouterFunction<ServerResponse> userRoutes(UserHandler handler) {
        return route(GET("/users/{id}"), handler::getUserById)
                .andRoute(POST("/users"), handler::createUser);
    }
}