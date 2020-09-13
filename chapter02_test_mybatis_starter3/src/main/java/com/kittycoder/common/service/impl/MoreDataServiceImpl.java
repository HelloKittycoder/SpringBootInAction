package com.kittycoder.common.service.impl;

import com.kittycoder.book.mapper.BookMapper;
import com.kittycoder.book.po.Book;
import com.kittycoder.common.service.MoreDataService;
import com.kittycoder.dyndatasource.TargetDataSource;
import com.kittycoder.student.mapper.StudentMapper;
import com.kittycoder.student.po.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 主要通过调用MoreDataServiceImpl类来测试动态切换数据源
 *
 * 核心逻辑：所有数据库访问都会经过DynamicDataSource这个数据源，由它来判断该使用哪个数据源
 * DynamicDataSourceAspect切面类会在使用数据源前，使用数据源后做一些干预（通过操作DynamicDataSourceContextHolder来实现的），
 *
 * DynamicDataSourceAspect切面类的相关逻辑如下：
 * 1.使用了TargetDataSource注解的，将会进到DynamicDataSourceAspect切面类里，解析填写的数据源id
 * 如果该数据源id存在，将使用提供的数据源，DynamicDataSourceContextHolder中设置该数据源id，DynamicDataSource自己会来取；
 * 如果该数据源id不存在，切面中会提示不存在，不对DynamicDataSourceContextHolder做操作，DynamicDataSource取到的是null，直接使用默认数据源
 * 数据源使用完毕，切面会将DynamicDataSourceContextHolder中的数据源id恢复成null
 *
 * 2.没有使用TargetDataSource注解，完全不进DynamicDataSourceAspect切面切面类，不对DynamicDataSourceContextHolder做操作，
 * DynamicDataSource取到的是null，直接使用默认数据源
 *
 * Created by shucheng on 2020/9/11 21:01
 */
@Service
public class MoreDataServiceImpl implements MoreDataService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private StudentMapper studentMapper;
    @Resource
    private BookMapper bookMapper;

    @TargetDataSource("datasource3")
    @Override
    public List<Student> selectStudentList() {
        logger.debug("MoreDataServiceImpl#selectStudentList--------begin");
        List<Student> list = studentMapper.selectStudentList();
        logger.debug("MoreDataServiceImpl#selectStudentList--------end");
        return list;
    }

    @TargetDataSource("datasource1")
    @Override
    public void insertStudent(Student student) {
        logger.debug("MoreDataServiceImpl#insertStudent--------begin");
        studentMapper.insertStudent(student);
        logger.debug("MoreDataServiceImpl#insertStudent--------end");
    }

    @Override
    public void updateStudent(Student student) {
        logger.debug("MoreDataServiceImpl#updateStudent--------begin");
        studentMapper.updateStudent(student);
        logger.debug("MoreDataServiceImpl#updateStudent--------end");
    }

    @TargetDataSource("datasource2")
    @Override
    public List<Book> selectBookList() {
        logger.debug("MoreDataServiceImpl#selectBookList--------begin");
        List<Book> list = bookMapper.selectBookList();
        logger.debug("MoreDataServiceImpl#selectBookList--------end");
        return list;
    }

    @TargetDataSource("datasource2")
    @Override
    public void insertBook(Book book) {
        logger.debug("MoreDataServiceImpl#insertBook--------begin");
        bookMapper.insertBook(book);
        logger.debug("MoreDataServiceImpl#insertBook--------end");
    }

    @TargetDataSource("datasource2")
    @Override
    public void updateBook(Book book) {
        logger.debug("MoreDataServiceImpl#updateBook--------begin");
        bookMapper.updateBook(book);
        logger.debug("MoreDataServiceImpl#updateBook--------begin");
    }
}
