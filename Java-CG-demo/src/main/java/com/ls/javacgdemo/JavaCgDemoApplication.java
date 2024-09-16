package com.ls.javacgdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication//首先排除掉系统默认数据源(exclude = {DataSourceAutoConfiguration.class})
@MapperScan(basePackages = {"com.ls.javacgdemo.mapper"})
public class JavaCgDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaCgDemoApplication.class, args);
	}

}
