//package com.ls.javacgdemo.config;
//
//import com.ls.javacgdemo.domain.DatabaseType;
//import org.springframework.boot.context.properties.ConfigurationProperties;
////import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import javax.sql.DataSource;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * @Description:
// * @Author: ls
// * @Date: 2024/7/30 22:32
// */
//@Configuration
//public class DataSourceConfig {
//
//    @Bean
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.datasource.master")
//    public DataSource primaryDataSource() {
//        return (DataSource) DataSourceBuilder.create().build(); // 创建主数据源
//    }
//
////    @Bean
////    @ConfigurationProperties(prefix = "spring.datasource.replica")
////    public DataSource replicaDataSource() {
////        return (DataSource) DataSourceBuilder.create().build(); // 创建从数据源
////    }
//
//    @Bean
//    public DataSource dataSource() {
//        Map<Object, Object> targetDataSources = new HashMap<>();
//        targetDataSources.put(DatabaseType.PRIMARY, primaryDataSource());
////        targetDataSources.put(DatabaseType.REPLICA, replicaDataSource());
//
//        DynamicDataSource dataSource = new DynamicDataSource();
//        dataSource.setDefaultTargetDataSource(primaryDataSource()); // 设置默认数据源
//        dataSource.setTargetDataSources(targetDataSources); // 设置目标数据源
//        return (DataSource) dataSource;
//    }
//}