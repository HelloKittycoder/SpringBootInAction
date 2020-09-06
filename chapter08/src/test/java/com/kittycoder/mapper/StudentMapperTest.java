package com.kittycoder.mapper;

import com.kittycoder.po.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shucheng on 2020/8/26 12:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentMapperTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private StudentMapper studentMapper;

    @Test
    public void test() {
        // logger.info("{}", dataSource);
        studentMapper.insertStudent(new Student(4, "赵六", 11, "1990-10-01"));
        studentMapper.insertStudent(new Student(5, "田七", 22, ""));
        List<Student> studentList = studentMapper.selectStudentList();
        System.out.println(studentList);
        studentMapper.updateStudent(new Student(4, "赵六", 11, ""));
        studentList = studentMapper.selectStudentList();
        System.out.println(studentList);
        studentMapper.deleteStudent(4);
        studentMapper.deleteStudent(5);
    }
}
