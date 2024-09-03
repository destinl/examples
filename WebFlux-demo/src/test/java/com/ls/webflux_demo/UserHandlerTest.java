package com.ls.webflux_demo;

import com.ls.webflux_demo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import com.ls.webflux_demo.entity.User;

import static org.mockito.Mockito.when;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/2 21:53
 */
@WebFluxTest
//@SpringBootTest
public class UserHandlerTest {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private UserRepository userRepository;
    @Test
    public void testGetUserById() {
        User user = new User();
        user.setId("1");
        user.setName("John");
        user.setAge(30);
        when(userRepository.findById("1")).thenReturn(Mono.just(user));
        webTestClient.get().uri("/users/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("John");
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setId("1");
        user.setName("John");
        user.setAge(30);
        when(userRepository.save(user)).thenReturn(Mono.just(user));
        webTestClient.post().uri("/users")
                .bodyValue(user)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.name").isEqualTo("John");
    }
}
