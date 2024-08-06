package com.ls.javacgdemo.config;

import com.ls.javacgdemo.domain.DataSource;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.naming.Context;
import javax.naming.InitialContext;

/**
 * @Description: 通过配置SqlSessionFactory和SqlSessionTemplate，我们可以确保MyBatis-Plus能够使用动态数据源。
 * @Author: ls
 * @Date: 2024/7/30 22:37
 */
@Configuration
//@MapperScan("com.ls.javacgdemo.mapper")
public class DynamicDataSourceConfig {

//    @Autowired
//    DataSource dataSource;
//    public DynamicDataSourceConfig(@Qualifier("primaryDataSource") DataSource dataSource) {
//        // 构造函数中使用
//        this.dataSource = dataSource;
//    }


//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
//        return factoryBuilder.build(dynamicDataSource);
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }

//    @Autowired
//    DynamicDataSource dynamicDataSource;
//    @Bean(name = "sqlSessionFactoryBean")
//    public SqlSessionFactoryBean sqlSessionFactoryBean() {
//        SqlSessionFactoryBean sqlSessionFactoryBean = null;
//        try {
//            // 加载JNDI配置
//            Context context = new InitialContext();
//            // 实例SessionFactory
//            sqlSessionFactoryBean = new SqlSessionFactoryBean();
//            // 配置数据源
//            sqlSessionFactoryBean.setDataSource(dynamicDataSource);
//            // 加载MyBatis配置文件
//            PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//            // 能加载多个，所以可以配置通配符(如：classpath*:mapper/**/*.xml)
////            sqlSessionFactoryBean.setMapperLocations(resourcePatternRe  solver.getResources(mapperLocations));
//        } catch (Exception e) {
//            System.out.println("创建SqlSession连接工厂错误：{}");
//        }
//        return sqlSessionFactoryBean;
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
//        SqlSessionTemplate sqlSessionTemplate=new SqlSessionTemplate(sqlSessionFactoryBean().getObject(), ExecutorType.BATCH);
//        return sqlSessionTemplate;
//    }

//    @Bean
//    public SqlSessionFactory sqlSessionFactory() throws Exception {
//        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
//        factoryBean.setDataSource(dynamicDataSource);
//        return factoryBean.getObject();
////        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
////        return factoryBuilder.build(dynamicDataSource);
//    }
//
//    @Bean
//    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
//        return new SqlSessionTemplate(sqlSessionFactory());
//    }
}
