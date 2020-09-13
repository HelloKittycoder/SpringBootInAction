package com.kittycoder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;

/**
 * Created by shucheng on 2020/8/26 12:43
 * exclude = {DataSourceAutoConfiguration.class}
 * 这段是关键，禁用默认的单数据源配置，不写的话，Service中注入Mapper会报循环依赖
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.kittycoder.**.mapper")
public class MybatisSpringBootApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(MybatisSpringBootApplication.class, args);
        // Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
