package com.ls.jasypt_demo;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/9/5 22:29
 */
@SpringBootTest
class DataDesensitizationShowcaseApplicationTests {

    /**
     * 注入加密方法
     */
    @Autowired
    private StringEncryptor encryptor;

    /**
     * 手动生成密文，此处演示了url，user，password
     */
    @Test
    public void encrypt() {
        String url = encryptor.encrypt("jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=false&allowMultiQueries=true&serverTimezone=Asia/Shanghai");
        String name = encryptor.encrypt("root");
        String password = encryptor.encrypt("Mysql112223");
        System.out.println("database url: " + url);
        System.out.println("database name: " + name);
        System.out.println("database password: " + password);
    }
}
