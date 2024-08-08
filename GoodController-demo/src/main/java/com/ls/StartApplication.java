package com.ls;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Description: 记得启动nacos服务 startup.cmd -m standalone
 * @Author: ls
 * @Date: 2024/8/2 21:02
 */
@SpringBootApplication
//@EnableDiscoveryClient
//@EnableConfigServer
//@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class StartApplication {

    public static void main(String[] args) {

        try {
            SpringApplication.run(StartApplication.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
