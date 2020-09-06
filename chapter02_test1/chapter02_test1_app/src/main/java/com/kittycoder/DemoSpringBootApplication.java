package com.kittycoder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by shucheng on 2020/8/20 9:44
 */
@SpringBootApplication
public class DemoSpringBootApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DemoSpringBootApplication.class, args);
        // Arrays.stream(ctx.getBeanDefinitionNames()).forEach(System.out::println);
        System.out.println(ctx.containsBean("student"));
    }
}
