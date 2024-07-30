//package com.ls.javacgdemo.config;
//
//import com.zendesk.maxwell.Maxwell;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.net.URISyntaxException;
//import java.sql.SQLException;
//
///**
// * @Description:
// * @Author: ls
// * @Date: 2024/7/30 22:39
// */
//@Configuration
//public class MaxwellConfig {
//
//    @Bean
//    public Maxwell maxwell() throws SQLException, URISyntaxException {
//        // 配置Maxwell进行实时数据同步
//        Maxwell maxwell = new Maxwell();
//        maxwell.setKafkaTopic("mysql_binlog");
//        maxwell.setKafkaBootstrapServers("localhost:9092");
//        return maxwell;
//    }
//}