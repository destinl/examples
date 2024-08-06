package com.ls.javacgdemo.config;

import com.ls.javacgdemo.domain.DataSource;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: ls
 * @Date: 2024/7/30 22:31
 */
@Aspect
@Component
public class DataSourceAspect {

    @Around("@annotation(dataSource)")
    public Object switchDataSource(ProceedingJoinPoint point, DataSource dataSource) throws Throwable {
        try {
            // 在方法执行前设置数据源
            DataSourceContextHolder.setDatabaseType(dataSource.value());
            return point.proceed(); // 执行目标方法
        } finally {
            // 在方法执行后清除数据源
            DataSourceContextHolder.clearDatabaseType();
        }
    }
}
