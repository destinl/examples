package com.ls.jasypt_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
public class JasyptDemoApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(JasyptDemoApplication.class, args);
        DataSource dataSource = context.getBean(DataSource.class);
        try (Connection connection = dataSource.getConnection()) {
            System.out.println("数据库连接成功！");
        } catch (SQLException e) {
            System.out.println("数据库连接失败！");
            e.printStackTrace();
        }
    }

}
