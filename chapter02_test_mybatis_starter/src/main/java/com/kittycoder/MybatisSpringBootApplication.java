package com.kittycoder;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by shucheng on 2020/8/26 12:43
 */
@SpringBootApplication
@MapperScan("com.kittycoder.**.mapper")
public class MybatisSpringBootApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(MybatisSpringBootApplication.class, args);
        // Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
