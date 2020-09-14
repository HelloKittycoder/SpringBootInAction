package com.kittycoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by shucheng on 2020/8/26 12:43
 */
@SpringBootApplication
public class JdbcSpringBootApplication {
    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(JdbcSpringBootApplication.class, args);
        // Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);
    }
}
