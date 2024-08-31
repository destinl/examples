package com.ls.jpademo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/31 15:17
 */
@Entity
@Table(name = "tb_teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, unique = true)
    private Long teacherId;

    @Column(nullable = false)
    private String teacherName;
}
