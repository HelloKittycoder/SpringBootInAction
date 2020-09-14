package com.kittycoder.student.repository;

import com.kittycoder.student.po.Student;

import java.util.List;

/**
 * Created by shucheng on 2020/9/14 0:21
 */
public interface StudentRepository {

    List<Student> selectStudentList();
    void insertStudent(Student student);
    void updateStudent(Student student);
}
