package com.ls.mall_tiny_sa_token;

import cn.dev33.satoken.SaManager;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@MapperScan("com.ls.mall_tiny_sa_token.mbg.mapper")
@SpringBootApplication
public class MallTinySaTokenApplication {

	public static void main(String[] args) {
		SpringApplication.run(MallTinySaTokenApplication.class, args);
		log.info("启动成功：Sa-Token配置如下：{}", SaManager.getConfig());
	}

}
