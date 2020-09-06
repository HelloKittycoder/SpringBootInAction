package com.kittycoder.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shucheng on 2020/8/31 9:16
 */
@Controller
public class DemoController {

    @RequestMapping("/fail")
    public String fail() {
        throw new RuntimeException("出错了");
    }
}
