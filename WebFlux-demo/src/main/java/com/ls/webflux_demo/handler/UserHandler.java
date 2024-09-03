package com.ls.webflux_demo.handler;

import com.ls.webflux_demo.repository.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import com.ls.webflux_demo.entity.User;
/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/2 21:48
 */
@Component
public class UserHandler {
    private final UserRepository userRepository;
    public UserHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<ServerResponse> getUserById(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<User> user = userRepository.findById(id);
        return user.flatMap(u -> ServerResponse.ok().bodyValue(u))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createUser(ServerRequest request) {
//        Mono<User> userMono = request.bodyToMono(User.class);
//        return userMono.flatMap(userRepository::save)
//                .flatMap(user -> ServerResponse.ok().bodyValue(user));
        User exampleUser = new User();
        exampleUser.setId("exampleId");
        exampleUser.setName("Example Name");
        exampleUser.setAge(35);

        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(exampleUser);
    }
}
