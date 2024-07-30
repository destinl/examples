package com.ls.javacgdemo.repository;

import com.ls.javacgdemo.domain.User;
import com.ls.javacgdemo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/28 15:35
 */
@Repository
public class UserRepositoryImpl implements UserRepository{
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    @Override
//    public User save(User user) {
//        String sql = "INSERT INTO t_user (username, password_plain) VALUES (?,?)";
//        jdbcTemplate.update(sql, user.getUsername(), user.getPasswordPlain());
//
//        // 获取插入后的自增 ID
//        String sqlForId = "SELECT LAST_INSERT_ID()";
//        int id = jdbcTemplate.queryForObject(sqlForId, Integer.class);
//        user.setId(id);
//
//        return user;
//    }
//
//    @Override
//    public Optional<User> findById(Integer id) {
//        String sql = "SELECT * FROM t_user WHERE id =?";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> {
//            User user = new User();
//            user.setId(rs.getInt("id"));
//            user.setUsername(rs.getString("username"));
//            return user;
//        }, id).stream().findFirst();
//    }
    private final UserMapper userMapper;

    @Autowired
    public UserRepositoryImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        userMapper.save(user);

//        // 获取插入后的自增 ID
//        String sqlForId = "SELECT LAST_INSERT_ID()";
//        int id = jdbcTemplate.queryForObject(sqlForId, Integer.class);
//        user.setId(id);

        return user;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userMapper.findById(id);
    }
}
