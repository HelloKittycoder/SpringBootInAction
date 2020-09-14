package com.kittycoder.student.repository;

import com.kittycoder.student.po.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shucheng on 2020/9/14 0:21
 */
@Repository
public class StudentRepositoryImpl implements StudentRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Student> selectStudentList() {
        return jdbcTemplate.query("select * from student",
                (rs, rowNum) -> new Student(rs.getInt("sid"), rs.getString("sname"),
                        rs.getInt("sage"), rs.getString("sbirthday"))
        );
    }

    @Override
    public void insertStudent(Student student) {
        jdbcTemplate.update("insert into student(sid, sname, sage, sbirthday)" +
                " values(?, ?, ?, ?)", student.getSid(), student.getSname(), student.getSage(), student.getSbirthday());
    }

    @Override
    public void updateStudent(Student student) {
        jdbcTemplate.update("update student set sbirthday = ? where sid = ?",
                student.getSbirthday(), student.getSid());
    }
}
