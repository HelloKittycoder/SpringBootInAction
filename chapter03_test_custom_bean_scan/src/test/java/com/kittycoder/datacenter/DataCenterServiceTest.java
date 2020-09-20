package com.kittycoder.datacenter;

import com.kittycoder.datacenter.common.PageInfo;
import com.kittycoder.datacenter.common.ResponseEntity;
import com.kittycoder.datacenter.po.Student;
import com.kittycoder.datacenter.po.Teacher;
import com.kittycoder.datacenter.service.StudentService;
import com.kittycoder.datacenter.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by shucheng on 2020/9/20 21:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataCenterServiceTest {

    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;

    @Test
    public void test() {
        // System.out.println(studentService);
        ResponseEntity<Student> studentResponseEntity = studentService.queryStudent();
        System.out.println(studentResponseEntity.getEntity());

        PageInfo<Student> studentPageInfo = studentService.queryStudentList();
        System.out.println(studentPageInfo.getList());

        ResponseEntity<Teacher> teacherResponseEntity = teacherService.queryTeacher();
        System.out.println(teacherResponseEntity.getEntity());

        PageInfo<Teacher> teacherPageInfo = teacherService.queryTeacherList();
        System.out.println(teacherPageInfo.getList());
    }
}
