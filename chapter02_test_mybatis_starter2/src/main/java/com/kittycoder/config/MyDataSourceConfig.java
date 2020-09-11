package com.kittycoder.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * spring+mybatis多数据源：添加多套配置
 * 参考链接：https://www.javaboy.org/2019/0407/mybatis-multi.html
 * Created by shucheng on 2020/9/11 15:53
 */
@Configuration
public class MyDataSourceConfig {

    @Primary
    @Bean("firstDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource firstDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean("secondDataSource")
    @ConfigurationProperties(prefix = "spring.datasource2")
    public DataSource secondDataSource() {
        return DataSourceBuilder.create().build();
    }

    // 这里不能写com.kittycoder.**.mapper，不然所有的Mapper用的都是同一个数据源了
    // @MapperScan(basePackages = "com.kittycoder.**.mapper", sqlSessionFactoryRef = "sqlSessionFactory1", sqlSessionTemplateRef = "sqlSessionTemplate1")
    @Configuration
    @MapperScan(basePackages = "com.kittycoder.student.mapper", sqlSessionFactoryRef = "sqlSessionFactory1", sqlSessionTemplateRef = "sqlSessionTemplate1")
    public static class MyBatisConfigOne {
        @Resource(name = "firstDataSource")
        private DataSource dsOne;

        @Bean
        public SqlSessionFactory sqlSessionFactory1() {
            SqlSessionFactory sessionFactory = null;
            try {
                SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
                bean.setDataSource(dsOne);
                return bean.getObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sessionFactory;
        }

        @Bean
        public SqlSessionTemplate sqlSessionTemplate1() {
            return new SqlSessionTemplate(sqlSessionFactory1());
        }
    }

    @Configuration
    @MapperScan(basePackages = "com.kittycoder.book.mapper", sqlSessionFactoryRef = "sqlSessionFactory2", sqlSessionTemplateRef = "sqlSessionTemplate2")
    public static class MyBatisConfigTwo {
        @Resource(name = "secondDataSource")
        private DataSource dsTwo;

        @Bean
        public SqlSessionFactory sqlSessionFactory2() {
            SqlSessionFactory sessionFactory = null;
            try {
                SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
                bean.setDataSource(dsTwo);
                return bean.getObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return sessionFactory;
        }

        @Bean
        public SqlSessionTemplate sqlSessionTemplate2() {
            return new SqlSessionTemplate(sqlSessionFactory2());
        }
    }
}
