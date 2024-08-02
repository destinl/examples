package com.ls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/2 21:02
 */
@SpringBootApplication
public class StartApplication {

    public static void main(String[] args) {

        try {
            SpringApplication.run(StartApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
