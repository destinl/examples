package com.ls.utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/8/27 22:18
 */
public class JDBCDruidUtils {
//    private static DataSource dataSource;
//
//    static {
//        Properties pro = new Properties();
//        try {
//            pro.load(JDBCDruidUtils.class.getClassLoader().getResourceAsStream("druid.properties"));
//            dataSource = DruidDataSourceFactory.createDataSource(pro);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static Connection getConnection() throws SQLException {
//        return dataSource.getConnection();
//    }
//
//    public static void close(Connection connection, Statement statement) {
//        if (connection!= null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//        if (statement!= null) {
//            try {
//                statement.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static void close(Connection connection, Statement statement, ResultSet resultSet) {
//        close(connection, statement);
//        if (resultSet!= null) {
//            try {
//                resultSet.close();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    public static DataSource getDataSource() {
//        return dataSource;
//    }
}
