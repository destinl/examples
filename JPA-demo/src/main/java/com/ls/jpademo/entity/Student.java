package com.ls.jpademo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/31 14:25
 */
@Entity
@Table(name = "tb_student")
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Integer age;
    @Column()
    private Long teacherId;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Student(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
    // set、get方法等...
}

