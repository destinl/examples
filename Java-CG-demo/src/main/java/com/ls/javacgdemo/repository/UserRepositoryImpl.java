
package com.ls.javacgdemo.repository;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ls.javacgdemo.domain.OrderInfo;
import com.ls.javacgdemo.domain.Product;
import com.ls.javacgdemo.domain.User;
//import com.ls.javacgdemo.mapper.UserMapper;
import com.ls.javacgdemo.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/28 15:35
 */

@Repository
//@DS("primary")
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

//    @Autowired
//    private SqlSessionTemplate sqlSessionTemplate;

//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

//    @Autowired
//    private  UserMapper userMapper;
//
//    @Autowired
//    public UserRepositoryImpl(UserMapper userMapper) {
//        this.userMapper = userMapper;
//    }

//    @Override
//    public User save(User user) {
////        userMapper.save(user);
////        // 获取插入后的自增 ID
////        String sqlForId = "SELECT LAST_INSERT_ID()";
////        int id = jdbcTemplate.queryForObject(sqlForId, Integer.class);
////        user.setId(id);
//
//        return user;
//    }
    @Override
    @DS("master")
    public User save(User user) {
        String sql = "INSERT INTO t_user (username, password_plain) VALUES (#{user.username}, #{user.passwordPlain})";
//        jdbcTemplate.update(sql, user.getUsername(), user.getPasswordPlain());
        return user;
    }

    public Optional<User> findById(Integer id) {
//        try (SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession()) {
//            String sql = "SELECT * FROM t_user WHERE id =?";
//            return sqlSession.selectOne(sql, id);
////                    .map(result -> {
////                        User user = new User();
////                        user.setId((Integer) result.get("id"));
////                        user.setUsername((String) result.get("username"));
////                        user.setPasswordPlain((String) result.get("password_plain"));
////                        return user;
////            });
//        }
        return null;
//        return userMapper.findById(id);
    }
//    @DS("replica")
//    public Optional<User> findById(Integer id) {
//        String sql = "SELECT * FROM t_user WHERE id =?";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> {
//            User user = new User();
//            user.setId(rs.getInt("id"));
//            user.setUsername(rs.getString("username"));
//            user.setPasswordPlain(rs.getString("password_plain"));
//            return user;
//        }, id).stream().findFirst();
//    }

    @Override
    public Optional<Product> findproductById(int id) {
//        String sql = "SELECT * FROM t_product WHERE id = ?";
//        return jdbcTemplate.query(sql, (rs, rowNum) -> {
//            Product user = new Product();
//            user.setId(rs.getInt("id"));
//            user.setProductCount(rs.getInt("product_count"));
//            user.setProductName(rs.getString("product_name"));
//            return user;
//        }, id).stream().findFirst();
        return null;
    }

    @Override
    public void insertSelective(OrderInfo orderInfo) {
        String sql = "INSERT INTO t_order_info (buy_name, buy_goods) VALUES (?,?)";
//        jdbcTemplate.update(sql, orderInfo.getBuyName(), orderInfo.getBuyGoods());
    }

    @Override
    public void updateByPrimaryKeySelective(Product updateProduct) {
        String sql = "UPDATE t_product SET product_count = ?, " +
                "product_name = ? WHERE id = ?";
//        Map<String, Object> params = new HashMap<>();
//        params.put("id", updateProduct.getId());
//        params.put("productCount", updateProduct.getProductCount());
//        params.put("productName", updateProduct.getProductName());

//        jdbcTemplate.update(sql ,updateProduct.getProductCount(), updateProduct.getProductName(), updateProduct.getId());
    }
}

