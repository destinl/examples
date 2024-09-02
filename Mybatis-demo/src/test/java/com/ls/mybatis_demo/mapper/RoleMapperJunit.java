package com.ls.mybatis_demo.mapper;

import com.ls.mybatis_demo.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/2 20:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class RoleMapperJunit {
    @Autowired
    private RoleMapper roleMapper;
    @Test
    public void test(){
        // 新增数据
        roleMapper.insert(new Role(1, "开发工程师", new Date()));
        roleMapper.insert(new Role(2, "测试工程师", new Date()));
        roleMapper.insert(new Role(3, "项目经理", new Date()));
        // 查询数据
        List<Role> roleList = roleMapper.findAll();
        System.out.println("第一次查询全部数据结果：" + roleList.toString());
        System.out.println("----------------------");
        // 修改单条数据
        roleMapper.update(new Role(1, "技术经理"));
        // 单条查询
        Role role = roleMapper.findById(1);
        System.out.println("查询[id=1]结果：" + role.toString());
        System.out.println("----------------------");
        // 删除单条数据
        roleMapper.delete(1);
        // 查询数据
        List<Role> roleList1 = roleMapper.findAll();
        System.out.println("第二次查询全部数据结果：" + roleList1.toString());
    }
}