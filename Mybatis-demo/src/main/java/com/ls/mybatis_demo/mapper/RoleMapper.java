package com.ls.mybatis_demo.mapper;

import org.apache.ibatis.annotations.*;

import com.ls.mybatis_demo.entity.Role;

import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/2 20:38
 */
public interface RoleMapper {
    @Select("select * from tb_role")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "roleName",  column = "role_name"),
            @Result(property = "createTime", column = "create_time")
    })
    List<Role> findAll();

    @Select("select * from tb_role where id =#{id}")
    @Results({
            @Result(property = "id",  column = "id"),
            @Result(property = "roleName",  column = "role_name"),
            @Result(property = "createTime", column = "create_time")
    })
    Role findById(@Param("id") Integer id);

    @Insert("insert into tb_role(id, role_name, create_time) VALUES(#{id}, #{roleName}, #{createTime})")
    int insert(Role role);

    @Update("update tb_role set role_name=#{roleName} WHERE id=#{id}")
    int update(Role role);

    @Delete("delete from tb_role where id =#{id}")
    int delete(@Param("id") Integer id);
}
