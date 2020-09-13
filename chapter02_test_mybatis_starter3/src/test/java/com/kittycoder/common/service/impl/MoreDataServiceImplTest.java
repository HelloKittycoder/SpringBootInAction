package com.kittycoder.common.service.impl;

import com.kittycoder.BaseTest;
import com.kittycoder.book.po.Book;
import com.kittycoder.common.service.MoreDataService;
import com.kittycoder.student.po.Student;
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
 * 测试springboot+mybatis多数据源（使用AOP）
 * Created by shucheng on 2020/9/11 21:07
 *
 * 参考链接：https://blog.csdn.net/xiaosheng_papa/article/details/80218006
 * https://www.jianshu.com/p/b2f818b742a2
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MoreDataServiceImplTest extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    // 默认数据源（H2）
    @Autowired
    private DataSource dataSource;
    // 第2个数据源（mysql）
    @Autowired
    @Qualifier("secondDataSource")
    private DataSource dataSource2;

    @Resource
    private MoreDataService moreDataService;

    /**
     * 可以看到studentMapper中的数据源是dataSource（H2），
     * bookMapper中的数据源是dataSource2（mysql）
     */
    @Test
    public void test() {
        // logger.info("{}", dataSource);
        logger.info("操作H2数据库");
        moreDataService.insertStudent(new Student(1, "张三", 11, "1990-10-01"));
        moreDataService.insertStudent(new Student(2, "李四", 22, ""));
        List<Student> studentList = moreDataService.selectStudentList();
        System.out.println(studentList);
        moreDataService.updateStudent(new Student(1, "张三", 11, ""));
        studentList = moreDataService.selectStudentList();
        System.out.println(studentList);

        logger.info("操作Mysql数据库");
        moreDataService.insertBook(new Book(1, "三国演义", "罗贯中"));
        moreDataService.insertBook(new Book(2, "红楼梦", "曹雪芹"));
        List<Book> bookList = moreDataService.selectBookList();
        System.out.println(bookList);
        moreDataService.updateBook(new Book(1, "三国演义2", "罗贯中2"));
        bookList = moreDataService.selectBookList();
        System.out.println(bookList);
    }

    /**
     * 下面的情况，已经明确指定了数据源，和DynamicDataSourceAspect没有关系
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        // H2数据源的初始化执行脚本
        executeSql(dataSource, "sql/h2_createTable.sql");
        // Mysql数据源的初始化执行脚本
        executeSql(dataSource2, "sql/mysql_createTable.sql");
    }
}
