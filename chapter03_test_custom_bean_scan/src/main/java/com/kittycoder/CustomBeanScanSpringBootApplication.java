package com.kittycoder;

import com.kittycoder.custombeanscan.DcScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by shucheng on 2020/9/20 13:18
 */
@SpringBootApplication
@DcScan("com.kittycoder.datacenter.service")
public class CustomBeanScanSpringBootApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(CustomBeanScanSpringBootApplication.class, args);
    }
}
