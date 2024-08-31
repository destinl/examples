package com.ls.jpademo.repository;

import com.ls.jpademo.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/31 14:27
 */
public interface StudentRepository extends JpaRepository<Student,Long> {
    /**
     * 自定义简单查询，通过姓名进行搜索
     * @param name
     * @return
     */

    Student findByName(String name);
    /**
     * 自定义简单查询，通过姓名和年龄进行统计
     * @param name
     * @return
     */
    Integer countByNameAndAge(String name, Integer age);

    /**
     * 自定义SQL语句，单条查询（注意：这里自定义的 SQL 语句并非数据库中的 SQL 语句，而是 hibernate 所支持 SQL 语句，简称 hsql，例如表名要采用 Java
     * 实体而非数据库中真实的表名，否则可能会报错。）
     * @param studentName
     * @return
     */
    @Query(value = "select s from Student s where s.name = ?1")
    Student findByStudentName(String studentName);

    /**
     * 自定义SQL语句，修改数据
     * @param name
     * @param age
     * @return
     */
    @Transactional
    @Modifying
    @Query(value = "update Student s set s.age = ?2 where s.name = ?1")
    int updateAgeByName(String name, Integer age);

    /**
     * 自定义SQL语句，删除数据
     * @param name
     */
    @Transactional
    @Modifying
    @Query(value = "delete from Student s where s.name = ?1")
    int deleteByName(String name);

    /**
     * 自定义简单查询，通过年龄进行分页搜索(注意自定义的方法不会自动进行count语句汇总查询，推荐采用模板方法来进行分页查询。)
     * @param age
     * @param pageable
     * @return
     */
    Page<Student> findByAge(Integer age, Pageable pageable);
}
