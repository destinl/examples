package com.ls.mybatis_demo.mapper;

import com.ls.mybatis_demo.entity.Menu;
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
 * @Date: 2024/9/2 20:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MenuMapperJunit {
    @Autowired
    private MenuMapper menuMapper;
    @Test
    public void test(){
        // 新增数据
        menuMapper.insert(new Menu(1, "用户菜单", new Date()));
        menuMapper.insert(new Menu(2, "角色菜单", new Date()));
        menuMapper.insert(new Menu(3, "系统菜单", new Date()));
        // 查询数据
        List<Menu> menuList = menuMapper.findAll();
        System.out.println("第一次查询全部数据结果：" + menuList.toString());
        System.out.println("----------------------");
        // 修改单条数据
        menuMapper.update(new Menu(1, "项目菜单"));
        // 单条查询
        Menu menu = menuMapper.findById(1);
        System.out.println("查询[id=1]结果：" + menu.toString());
        System.out.println("----------------------");
        // 删除单条数据
        menuMapper.delete(1);
        // 查询数据
        List<Menu> menuList1 = menuMapper.findAll();
        System.out.println("第二次查询全部数据结果：" + menuList1.toString());
    }
}
