//package com.ls.ssm_exp.connect;
//
////import com.alibaba.druid.pool.DruidDataSource;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import com.ls.ssm_exp.config.DruidConfig;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
////import org.springframework.test.context.junit4.SpringRunner;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
///**
// * @Description:
// * @Author: ls
// * @Date: 2024/6/2920:27
// */
////@RunWith(SpringRunner.class)
//@SpringBootTest // 启动Spring Boot测试环境，它会加载application.yml中的配置
//public class ConnectionTest {
//
//    @Autowired
//    private DataSource dataSource;
//
//    public static void main(String[] args) throws SQLException {
////        Driver driver = new Driver();
////        // 创建连接
////        Connection con = driver.connect(JDBC_URL, props);
////        Statement statement = con.createStatement();
////        ResultSet resultSet = statement.executeQuery("show tables");
////        while (resultSet.next()) {
////            System.out.println(resultSet.getString(1));
////        }
////        con.close();
//
////        DruidDataSource druidDataSource = new DruidDataSource();
////// 数据源配置
////        druidDataSource.setUrl(druidConfigJDBC_URL);
////        druidDataSource.setUsername(USERNAME);
////        druidDataSource.setPassword(PASSWORD);
////        DruidDataSource druidDataSource = (DruidDataSource) druidConfig.dataSource();
//////
////////        // 初始化
////////        druidDataSource.wait();
////        // 获取表名
////        Connection con = druidDataSource.getConnection();
////        Statement statement = con.createStatement();
////        ResultSet resultSet = statement.executeQuery("show tables");
////        while (resultSet.next()) {
////            System.out.println(resultSet.getString(1));
////        }
////        con.close();
//    }
//
//    @Test
//    public void testGetConnection() throws SQLException {
//        // druid 数据源
//        DruidDataSource druidDataSource = new DruidDataSource();
//// 数据源配置
//        druidDataSource.setUrl("jdbc:mysql://localhost:3306/testdb?allowPublicKeyRetrieval=true&useSSL=true&requireSSL=false");
//        druidDataSource.setUsername("root");
//        druidDataSource.setPassword("3333");
//// 初始化
//        druidDataSource.init();
//// 获取表名
//        Connection con = druidDataSource.getConnection();
//        Statement statement = con.createStatement();
//        ResultSet resultSet = statement.executeQuery("show tables");
//        while (resultSet.next()) {
//            System.out.println(resultSet.getString(1));
//        }
//        con.close();
////        try (
////                DruidDataSource druidDataSource = new DruidDataSource();
////                // 数据源配置
////                druidDataSource.setUrl(JDBC_URL);
////                druidDataSource.setUsername(USERNAME);
////                druidDataSource.setPassword(PASSWORD);
////// 初始化
////                druidDataSource.init();
////// 获取表名
////                Connection con = druidDataSource.getConnection();
////                Statement statement = con.createStatement();
////                ResultSet resultSet = statement.executeQuery("show tables");
////                while(resultSet.next()) {
////            System.out.println(resultSet.getString(1));
////        }
////        con.close();
////        Connection con = dataSource.getConnection();
////        Statement statement = con.createStatement();
////        ResultSet resultSet = statement.executeQuery("show tables")){
////
////            while (resultSet.next()) {
////                System.out.println(resultSet.getString(1));
////                }
////        } catch (SQLException e) {
////            e.printStackTrace();
////        }
//    }
//}
