package com.ls.ssm_exp.utils;

//import org.jeecg.modules.system.service.impl.SysDictServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
//import org.jeecg.modules.system.service.impl.SysDictServiceImpl;


/**
 * @Description:
 * @Author: ls
 * @Date: 2024/6/3021:31
 */

@Component
@Order(1)
public class CacheDataWarmup implements ApplicationRunner {

    private final RedisTemplate redisTemplate;

    @Autowired
    public CacheDataWarmup(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // 设置键名
        String redisKey = "test:count";
        try{
            // 设置键值
            redisTemplate.opsForValue().set(redisKey, 1, Duration.ofMinutes(1));
            // 获取该键
            System.out.println(redisTemplate.opsForValue().get(redisKey));
//            // 键值加3
            System.out.println(redisTemplate.opsForValue().increment(redisKey,3));
//            // 键值减3
            System.out.println(redisTemplate.opsForValue().decrement(redisKey,3));

        } catch (Exception e) {
            e.printStackTrace();
            // 处理异常或记录日志
        }

    }
}
