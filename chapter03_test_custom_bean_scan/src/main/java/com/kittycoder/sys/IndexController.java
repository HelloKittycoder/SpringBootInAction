package com.kittycoder.sys;

import com.kittycoder.datacenter.common.ResponseEntity;
import com.kittycoder.datacenter.po.Student;
import com.kittycoder.datacenter.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by shucheng on 2020/9/20 13:52
 */
@Controller
public class IndexController {

    @Autowired
    private StudentService studentService;

    // http://localhost:8080/
    @RequestMapping
    public String index() {
        ResponseEntity<Student> studentResponseEntity = studentService.queryStudent();
        System.out.println(studentResponseEntity.getEntity());
        return "index";
    }
}
