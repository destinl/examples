package com.ls.javacgdemo.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.ls.javacgdemo.domain.*;
//import com.ls.javacgdemo.mapper.UserMapper;
import com.ls.javacgdemo.mapper.UserMapper;
import com.ls.javacgdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/28 14:31
 */

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    private Lock lock = new ReentrantLock(true);

    @Resource
    @Lazy
    private UserService userService;


    public User saveUser(User user) {
        return userRepository.save(user);
    }



    @DataSource(DatabaseType.REPLICA) // 从 库 读取用户
//    @DS("replica")
    public Optional<User> findById(Integer id) {
        return userMapper.findById(id);
//        return userRepository.findById(id);
    }

    //正确 加锁 和 使用@Transactional 的方法(或者@Transactional(isolation = Isolation.SERIALIZABLE)串行化，但太耗性能)
    public void lockPoduct(){
        try{
            lock.lock();
            userService.sellProduct();
        }finally {
            lock.unlock();
        }
    }

    //错误 加锁 和 使用@Transactional 的方法（只使用下面的加锁方式）
    @Transactional
    public void sellProduct(){
        //在这使用lock 或者 synchronized 都不行
        try{
//            lock.lock();
            System.out.println(Thread.currentThread().getName() + "：抢到锁了，进入方法");
            Optional<Product> product0 = userRepository.findproductById(1);
            Product product = product0.orElse(new Product()); ;
            Integer productCount = product.getProductCount();
            String productName = product.getProductName();
            System.out.println(Thread.currentThread().getName() + "：当前名"+ productName);
            if(productCount > 0){
                Product updateProduct = new Product();
                updateProduct.setId(product.getId());
                updateProduct.setProductCount(productCount-1);
                updateProduct.setProductName(productName);
                userRepository.updateByPrimaryKeySelective(updateProduct);
                OrderInfo orderInfo = new OrderInfo();
                orderInfo.setBuyName(Thread.currentThread().getName());
                orderInfo.setBuyGoods(product.getProductName());
                userRepository.insertSelective(orderInfo);
                System.out.println(Thread.currentThread().getName() + "：减库存，创建订单完成");
            }else{
                System.out.println(Thread.currentThread().getName() + "：没库存，释放锁");
            }
        }finally {
//            lock.unlock();
        }
        System.out.println("释放锁");
    }
}