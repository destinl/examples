package com.ls.javacgdemo.service;

import com.ls.javacgdemo.domain.DataSource;
import com.ls.javacgdemo.domain.DatabaseType;
import com.ls.javacgdemo.domain.User;
import com.ls.javacgdemo.mapper.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/30 23:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @Test
    @DataSource(DatabaseType.REPLICA)
    public void testGetAllUsersFromReplica() {
        // 从从库读取所有用户
        Optional<User> users = userService.findById(1);
        // 验证返回的用户列表不为空
        Assert.assertNotNull(users);
    }

    @Test
    @DataSource(DatabaseType.PRIMARY)
    public void testGetAllUsersFromPrimary() {
        // 从主库读取所有用户
        Optional<User> users = userService.findById(1);
        // 验证返回的用户列表不为空
        Assert.assertNotNull(users);
    }

    @Test
    public void testGetTagsById() {
//        List<String> tags = userMapper.getTagsById(8);
//        assertEquals(3, tags.size());
//        assertEquals("apple", tags.get(0));
//        assertEquals("banana", tags.get(1));
//        assertEquals("orange", tags.get(2));
    }
}
