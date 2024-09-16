package com.ls.javacgdemo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ls.javacgdemo.domain.User;
import com.ls.javacgdemo.handler.StringListTypeHandler;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.util.List;
import java.util.Optional;

//@Mapper
public interface UserMapper {

    @Select("INSERT INTO t_user (username, password_plain) VALUES (#{tags, typeHandler= com.ls.javacgdemo" +
            ".handler.StringListTypeHandler}," +
            " #{passwordPlain})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void save(User user);

//    @DS("replica")
    @Select("SELECT id, username, password_plain as passwordPlain  FROM t_user WHERE" +
            " id = #{id}")
    Optional<User> findById(@Param("id") Integer id);

    @Select("SELECT username FROM t_user WHERE id = #{id}")
    @Results({
            @Result(column = "username", property = "tags", typeHandler = StringListTypeHandler.class)
    })
    //, jdbcType = JdbcType.VARCHAR
    List<String> getTagsById(@Param("id") Integer id);

}