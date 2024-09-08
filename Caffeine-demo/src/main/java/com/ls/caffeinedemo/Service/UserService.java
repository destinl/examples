package com.ls.caffeinedemo.Service;

import com.ls.caffeinedemo.domain.entity.User;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/1216:58
 */
@Service
public class UserService {
    @CachePut(value = "cache1", key = "#userId")
    public User getUserById(String userId) {
        // 模拟从数据库查询用户信息
        return new User(userId, "UserName");
    }

    @CachePut(value = "cache1", key = "#user.id")
    public User updateUser(User user) {
        // 模拟更新数据库用户信息
        return user;
    }

    @CacheEvict(value = "cache1", key = "#userId")
    public void deleteUser(String userId) {
        // 模拟删除数据库用户信息
    }
}
