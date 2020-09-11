package com.kittycoder.student.mapper;

import com.kittycoder.student.po.Student;

import java.util.List;

/**
 * Created by shucheng on 2020/8/26 17:09
 */
public interface StudentMapper {

    List<Student> selectStudentList();
    void insertStudent(Student student);
    void updateStudent(Student student);
}
