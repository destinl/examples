package com.ls.jpademo.repository;

import com.ls.jpademo.entity.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/31 14:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentJPATest {
    @Autowired
    private StudentRepository studentRepository;
    @Test
    public void test(){
        // 插入3条数据
        studentRepository.save(new Student("张三", 20));
        studentRepository.save(new Student("李四", 21));
        studentRepository.save(new Student("王五", 22));
        // 查询全部数据
        List<Student> dbList = studentRepository.findAll();
        System.out.println("第一次全量查询结果：" + dbList.toString());
        System.out.println("------------------------");
        // 修改数据
        studentRepository.save(new Student(dbList.get(0).getId(),"赵六", 20));
        // 查询指定数据
        Optional<Student> findResult = studentRepository.findById(dbList.get(0).getId());
        System.out.println("查询第一条数据结果：" + findResult.toString());
        System.out.println("-----------------");
        // 删除数据
        studentRepository.deleteById(dbList.get(0).getId());
        // 查询全部数据
        List<Student> result = studentRepository.findAll();
        System.out.println("第二次全量查询结果：" + result.toString());
    }

    @Test
    public void simpleTest(){
        Student result1 = studentRepository.findByName("李四");
        System.out.println("第一次查询结果：" + result1.toString());
        System.out.println("-----------------");
        Integer result2 = studentRepository.countByNameAndAge("王五", 22);
        System.out.println("第二次查询结果：" + result2);
    }

    @Test
    public void sqlTest(){
        // 新增
        studentRepository.save(new Student("王五", 22));
        // 查询
        Student result1 = studentRepository.findByStudentName("王五");
        System.out.println("第一次查询结果：" + result1.toString());
        System.out.println("-----------------");
        // 修改
        studentRepository.updateAgeByName("王五", 30);
        Student result2 = studentRepository.findByStudentName("王五");
        System.out.println("第二次查询结果：" + result2.toString());
        System.out.println("-----------------");
        // 删除
        studentRepository.deleteByName("王五");
        Student result3 = studentRepository.findByStudentName("王五");
        System.out.println("第三次查询结果：" + result3);
    }

    @Test
    public void pageTest(){
        // 构建分页参数
        int page=0,size=10;
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page, size, sort);
        // 构建单条件查询参数
        Student param = new Student();
        param.setAge(21);
        Example<Student> example = Example.of(param);
        // 发起分页查询
        Page<Student> result = studentRepository.findAll(example, pageable);
        System.out.println("查询结果，总行数：" + result.getTotalElements());
        System.out.println("查询结果，明细：" + result.getContent());
    }


}
