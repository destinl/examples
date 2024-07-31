//package com.ls.javacgdemo.config;
//
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//
///**
// * @Description:
// * @Author: ls
// * @Date: 2024/7/30 22:29
// */
//public class DynamicDataSource extends AbstractRoutingDataSource {
//    @Override
//    protected Object determineCurrentLookupKey() {
//        // 从上下文中获取当前数据源的类型
//        return DataSourceContextHolder.getDatabaseType();
//    }
//}
