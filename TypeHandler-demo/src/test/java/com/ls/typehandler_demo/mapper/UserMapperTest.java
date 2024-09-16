package com.ls.typehandler_demo.mapper;

import com.ls.typehandler_demo.domain.entity.Address;
import com.ls.typehandler_demo.domain.entity.Tags;
import com.ls.typehandler_demo.domain.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/16 12:28
 */
@SpringBootTest
//@RunWith(SpringRunner.class)
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        // 可以在每个测试方法执行前进行一些初始化操作，比如清空表数据等
    }

    @Test
    void testInsertAndFindById() {
        User user = new User();
        user.setName("Test User");
        // 创建一个地址对象
        Address address = new Address();
        address.setCity("广州市");
        address.setStreet("天河区棠下街道");
        user.setAddress(address);

        // 插入用户
        int insertedId = userMapper.insert(user);

        // 断言插入成功并且返回了正确的自增 ID
        assertNotNull(insertedId);

        // 根据插入的 ID 查询用户
        User foundUser = userMapper.findById(insertedId);

        // 断言查询到的用户不为 null 且地址信息正确
        assertNotNull(foundUser);
        assertEquals(address.getCity(), foundUser.getAddress().getCity());
        assertEquals(address.getStreet(), foundUser.getAddress().getStreet());
    }

    @Test
    void testTagsInsertAndFindById() {
        Tags user = new Tags();
        user.setTags(Arrays.asList("apple", "banana", "orange"));

        // 插入用户
        int insertedId = userMapper.insertTags(user);

        // 断言插入成功并且返回了正确的自增 ID
        assertNotNull(insertedId);

        // 根据插入的 ID 查询用户
        Tags tagsEntity = userMapper.findTagsById(insertedId);

        // 断言返回值类型是 List<String>
        assertNotNull(tagsEntity);
        assertTrue(tagsEntity.getTags() instanceof List);

        // 断言标签列表内容正确
        assertEquals(Arrays.asList("apple", "banana", "orange"), tagsEntity.getTags());
    }
}
