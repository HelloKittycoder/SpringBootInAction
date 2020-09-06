package com.kittycoder.config;

import com.kittycoder.po.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shucheng on 2020/8/20 9:44
 */
@ConditionalOnClass(name = "com.kittycoder.po.Person")
@Configuration
public class AppConfig {

    // @Conditional(StudentCondition.class)
    // @ConditionalOnClass(Student.class)
    @Bean
    public Student student() {
        return new Student(101, "张三");
    }
}
