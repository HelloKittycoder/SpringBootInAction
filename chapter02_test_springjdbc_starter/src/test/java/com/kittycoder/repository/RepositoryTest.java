package com.kittycoder.repository;

import com.kittycoder.BaseTest;
import com.kittycoder.book.po.Book;
import com.kittycoder.book.repository.BookRepository;
import com.kittycoder.student.po.Student;
import com.kittycoder.student.repository.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;

/**
 * 测试spring-jdbc多数据源
 * Created by shucheng on 2020/8/26 12:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTest extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 默认数据源（H2）
    @Autowired
    private DataSource dataSource;
    // 第2个数据源（mysql）
    @Autowired
    @Qualifier("secondDataSource")
    private DataSource dataSource2;

    @Resource
    private StudentRepository studentMapper;
    @Resource
    private BookRepository bookMapper;

    /**
     * 可以看到studentMapper中的数据源是dataSource（H2），
     * bookMapper中的数据源是dataSource2（mysql）
     */
    @Test
    public void test() {
        // logger.info("{}", dataSource);
        logger.info("操作H2数据库");
        studentMapper.insertStudent(new Student(1, "张三", 11, "1990-10-01"));
        studentMapper.insertStudent(new Student(2, "李四", 22, ""));
        List<Student> studentList = studentMapper.selectStudentList();
        System.out.println(studentList);
        studentMapper.updateStudent(new Student(1, "张三", 11, ""));
        studentList = studentMapper.selectStudentList();
        System.out.println(studentList);

        logger.info("操作Mysql数据库");
        bookMapper.insertBook(new Book(1, "三国演义", "罗贯中"));
        bookMapper.insertBook(new Book(2, "红楼梦", "曹雪芹"));
        List<Book> bookList = bookMapper.selectBookList();
        System.out.println(bookList);
        bookMapper.updateBook(new Book(1, "三国演义2", "罗贯中2"));
        bookList = bookMapper.selectBookList();
        System.out.println(bookList);
    }

    @Before
    public void setUp() throws Exception {
        // H2数据源的初始化执行脚本
        executeSql(dataSource, "sql/h2_createTable.sql");
        // Mysql数据源的初始化执行脚本
        executeSql(dataSource2, "sql/mysql_createTable.sql");
    }
}
