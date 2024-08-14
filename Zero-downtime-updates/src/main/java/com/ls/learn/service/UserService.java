package com.ls.learn.service;

import com.ls.learn.domain.vo.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/14 22:42
 */

@Service
public class UserService {

    private static final List<User> DATAS = List.of(
            new User(1L, "张三", "男", 22),
            new User(2L, "李四", "男", 23),
            new User(3L, "王五", "女", 22),
            new User(4L, "赵六", "男", 32)) ;

    public List<User> queryUsers() {
        sleep(2000);
        return DATAS ;
    }
    public User queryById(Long id) {
        sleep(1000);
        return DATAS.stream().filter(user -> user.getId() == id).findFirst().orElse(null) ;
    }

    private void sleep(int time) {
        // 模拟耗时
        try {
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(time)) ;
        } catch (InterruptedException e) {}
    }
}