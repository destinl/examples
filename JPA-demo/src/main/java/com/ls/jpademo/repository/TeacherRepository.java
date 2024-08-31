package com.ls.jpademo.repository;

import com.ls.jpademo.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    /**
     * 自定义链表查询
     * @return
     */
    @Query(value = "select s.id, s.name, t.teacher_id as teacherId, t.teacher_name as teacherName from tb_student s " +
            "left" +
            " join tb_teacher t on s.teacher_id = t.teacher_id ", nativeQuery = true)
    List<Map<String,Object>> findCustomer();
}
