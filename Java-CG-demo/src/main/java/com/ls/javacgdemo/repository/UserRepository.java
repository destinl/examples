package com.ls.javacgdemo.repository;

import com.ls.javacgdemo.domain.OrderInfo;
import com.ls.javacgdemo.domain.Product;
import com.ls.javacgdemo.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/28 14:30
 */
@Repository
public interface UserRepository{
    User save(User user);

    Optional<User> findById(Integer id);

    Optional<Product> findproductById(int i);

    void insertSelective(OrderInfo orderInfo);

    void updateByPrimaryKeySelective(Product updateProduct);
//     extends JpaRepository<User, Integer>
//    @Override
//    <S extends User> S save(S user);
//
//    @Override
//    Optional<User> findById(Integer id);
}