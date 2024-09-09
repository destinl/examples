package com.ls.caffeinedemo.controller;

import com.ls.caffeinedemo.Service.UserService;
import com.ls.caffeinedemo.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.reactive.function.BodyInserters;
//import org.springframework.web.reactive.function.server.RouterFunction;
//import org.springframework.web.reactive.function.server.RouterFunctions;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import reactor.core.publisher.Mono;

//import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
//import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/8 22:41
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    //这个也是webflux
//    @Bean
//    public RouterFunction<ServerResponse> route() {
//        return RouterFunctions
//                .route(GET("/users/{userId}").and(accept(MediaType.TEXT_PLAIN)),
//                        request -> {
//                            User user = new User("1", "John");
//                            Mono<User> userMono = Mono.just(user);
//                            return ServerResponse.ok()
//                                    .contentType(MediaType.APPLICATION_JSON)
//                                    .body(userMono, User.class);
//                        });
//    }

//    @GetMapping("/webflux")
//    @ResponseStatus(HttpStatus.OK)
//    public Mono<User> getUserById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHdr) {
//        String jwtString = authHdr.replace("Bearer","");
//        //import io.jsonwebtoken.Jwts
////        Claims claims = Jwts.parser()
////                .setSigningKey(jwtSecret.getBytes())
////                .parseClaimsJws(jwtString).getBody();
////        return userService.findById((String)claims.get("email"));
//        User user = new User("1", "John");
//        Mono<User> userMono = Mono.just(user);
//        //可以通过subscribe()方法来消费这个响应式流
//        userMono.subscribe(System.out::println);
//        return userMono;
//
//    }

}
