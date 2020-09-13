package com.kittycoder.common.service;

import com.kittycoder.book.po.Book;
import com.kittycoder.student.po.Student;

import java.util.List;

/**
 * Created by shucheng on 2020/9/11 20:59
 */
public interface MoreDataService {

    List<Student> selectStudentList();
    void insertStudent(Student student);
    void updateStudent(Student student);

    List<Book> selectBookList();
    void insertBook(Book book);
    void updateBook(Book book);
}
