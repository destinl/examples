package com.ls.jasypt_demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootApplication
@EnableAspectJAutoProxy
public class JasyptDemoApplication {
//    private static final Logger logger = LoggerFactory.getLogger(JasyptDemoApplication.class);
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

//    @Override
//    public void run(String... args) throws Exception {
//        logger.info("用户名: 张三, 身份证: 123456789012345678, 手机号: 13800138000, 地址: 北京市朝阳区幸福街123号");
//    }
}
