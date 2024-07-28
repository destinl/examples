package com.ls.javacgdemo.service;

import com.ls.javacgdemo.domain.User;
import com.ls.javacgdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/28 14:31
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }
}