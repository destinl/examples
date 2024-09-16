package com.ls.typehandler_demo.mapper;

import com.ls.typehandler_demo.domain.entity.User;
import com.ls.typehandler_demo.typehandler.AddressTypeHandler;
import org.apache.ibatis.annotations.*;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/16 12:15
 */
//@Mapper
public interface UserMapper {

    @Select("SELECT id, name, address FROM user WHERE id = #{id}")
    @Results({
            @Result(column = "address", property = "address", typeHandler = AddressTypeHandler.class)
    })
    User findById(int id);

    @Insert("INSERT INTO user(name, address) VALUES(#{name}, #{address, typeHandler=com.ls.typehandler_demo" +
            ".typehandler.AddressTypeHandler})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(User user);
}
