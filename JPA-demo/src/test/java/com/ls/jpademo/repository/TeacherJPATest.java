package com.ls.jpademo.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/31 15:18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherJPATest {
    @Autowired
    private TeacherRepository teacherRepository;
    @Test
    public void test2(){
        // 查询全部数据
        List<Map<String,Object>> dbList = teacherRepository.findCustomer();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonString = objectMapper.writeValueAsString(dbList);
            System.out.println("查询结果：" + jsonString);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}