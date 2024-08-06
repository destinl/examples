package com.ls.javacgdemo.config;

import com.ls.javacgdemo.domain.DatabaseType;

/**
 * @Description: 可以在不同的线程中安全地设置和获取当前数据源的类型
 * @Author: ls
 * @Date: 2024/7/30 22:28
 */
public class DataSourceContextHolder {
    // 使用ThreadLocal存储当前线程的数据源类型
    private static final ThreadLocal<DatabaseType> contextHolder = new ThreadLocal<>();

    // 设置数据源类型
    public static void setDatabaseType(DatabaseType type) {
        contextHolder.set(type);
    }

    // 获取数据源类型
    public static DatabaseType getDatabaseType() {
        return contextHolder.get();
    }

    // 清除数据源类型
    public static void clearDatabaseType() {
        contextHolder.remove();
    }
}
