package com.ls.javacgdemo.service;

import com.ls.javacgdemo.domain.DataSource;
import com.ls.javacgdemo.domain.DatabaseType;
import com.ls.javacgdemo.domain.User;
import com.ls.javacgdemo.mapper.UserMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/30 23:08
 */
//@RunWith(SpringRunner.class)
//@ExtendWith(SpringExtension.class)
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
        User user = new User();
//        user.setUsername();Name("Test User");
        user.setPasswordPlain("222");
        user.setTags(Arrays.asList("apple", "banana", "orange"));

        // 保存用户并获取自增 ID
        userMapper.save(user);

        // 断言插入成功并且返回了正确的自增 ID
//        assertNotNull(insertedId);

        // 根据 ID 获取标签列表
        List<String> tags = userMapper.getTagsById(12);

        // 断言返回值类型是 List<String>
        assertNotNull(tags);
        assertTrue(tags instanceof List);

        // 断言标签列表内容正确
        assertEquals(Arrays.asList("apple", "banana", "orange"), tags);
    }
}
