package com.ls.ssm_exp.utils;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/318:17
 */

@Component
@Order(3)
public class LoadOuterResource implements ApplicationRunner {

    private final ConfigurableEnvironment environment ;
    public LoadOuterResource(ConfigurableEnvironment environment) {
        this.environment = environment ;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        YamlPropertySourceLoader sourceLoader = new YamlPropertySourceLoader() ;
        List<PropertySource<?>> list;
        try {
            //预先加载外部资源文件
            list = sourceLoader.load("pack", new ClassPathResource("com/pack/binder/properties/pack.yml"));
            list.forEach(propertySource -> environment.getPropertySources().addLast(propertySource)) ;
        } catch (IOException e) {
            e.printStackTrace() ;
        }
    }
}