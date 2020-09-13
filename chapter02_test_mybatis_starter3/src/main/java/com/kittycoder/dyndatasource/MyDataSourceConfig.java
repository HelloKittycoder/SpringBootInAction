package com.kittycoder.dyndatasource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.*;

/**
 * Created by shucheng on 2020/9/11 15:53
 */
@Configuration
public class MyDataSourceConfig {

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

    /**
     * 动态数据源：通过AOP在不同数据源之间动态切换
     * @return
     */
    @Primary
    @Bean("dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        // 默认数据源
        dynamicDataSource.setDefaultTargetDataSource(firstDataSource());
        // 配置多数据源
        Map<Object, Object> datasourceMap = new HashMap<>();
        datasourceMap.put("datasource1", firstDataSource());
        datasourceMap.put("datasource2", secondDataSource());

        dynamicDataSource.setTargetDataSources(datasourceMap);

        /**
         * Set<Object>怎么转换成Set<String>
         * 参考链接：https://stackoverflow.com/questions/24973624/convert-setobject-to-collectionstring
         */
        DynamicDataSourceContextHolder.getDataSourceIds().addAll((Set<String>) (Set<?>)(datasourceMap.keySet()));
        return dynamicDataSource;
    }

    // 配置@Transactional注解事务
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
