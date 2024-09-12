package com.ls.state_machine_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ls.state_machine_demo.mapper")
public class StateMachineDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(StateMachineDemoApplication.class, args);
    }

}
