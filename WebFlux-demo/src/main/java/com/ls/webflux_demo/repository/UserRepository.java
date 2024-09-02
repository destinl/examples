package com.ls.webflux_demo.repository;

import com.ls.webflux_demo.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/2 21:46
 */
public interface UserRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByName(String name);
}
