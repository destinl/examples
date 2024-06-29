package com.ls.ssm_exp.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/2911:30
 */
@Repository
public class MyRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MyRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void someDatabaseOperation(String name) {
        String sql = "INSERT INTO device(name) VALUES( ?)";
        jdbcTemplate.update(sql, name); // 替换 id 和 value 为实际的参数
    }
}