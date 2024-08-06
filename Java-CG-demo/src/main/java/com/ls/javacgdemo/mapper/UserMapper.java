package com.ls.javacgdemo.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ls.javacgdemo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Optional;

@Mapper
public interface UserMapper {

    @Select("INSERT INTO t_user (username, password_plain) VALUES (#{user.username}, #{user.passwordPlain})")
    void save(User user);

//    @DS("replica")
    @Select("SELECT id, username, password_plain as passwordPlain  FROM t_user WHERE" +
            " id = #{id}")
    Optional<User> findById(@Param("id") Integer id);
}