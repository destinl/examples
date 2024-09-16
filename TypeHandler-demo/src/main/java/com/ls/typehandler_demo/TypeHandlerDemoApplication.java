package com.ls.typehandler_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ls.typehandler_demo.mapper")
public class TypeHandlerDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TypeHandlerDemoApplication.class, args);
	}

}
