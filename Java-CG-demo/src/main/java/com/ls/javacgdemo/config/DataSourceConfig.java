package com.ls.javacgdemo.config;

import com.ls.javacgdemo.domain.DatabaseType;
import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 通过这个配置类，我们可以将多个数据源注册到Spring容器中，并设置默认数据源和目标数据源。
 * @Author: ls
 * @Date: 2024/7/30 22:32
 */
@Configuration
public class DataSourceConfig {

    @Bean(name = "primaryDataSource")

    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.master")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build(); // 创建主数据源
    }

    @Bean(name = "replicaDataSource")

    @ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.replica")
    public DataSource replicaDataSource() {
        return  DataSourceBuilder.create().build(); // 创建从数据源
    }

    @Bean
    @Primary
    public DataSource dataSource() {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DatabaseType.PRIMARY, primaryDataSource());
        targetDataSources.put(DatabaseType.REPLICA, replicaDataSource());

        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setDefaultTargetDataSource(primaryDataSource()); // 设置默认数据源
        dataSource.setTargetDataSources(targetDataSources); // 设置目标数据源
        return dataSource;
    }
}