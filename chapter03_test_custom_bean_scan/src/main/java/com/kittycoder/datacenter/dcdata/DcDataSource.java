package com.kittycoder.datacenter.dcdata;

import com.kittycoder.datacenter.common.DcType;
import com.kittycoder.datacenter.common.PageInfo;
import com.kittycoder.datacenter.common.ResponseEntity;
import com.kittycoder.datacenter.po.Student;
import com.kittycoder.datacenter.po.Teacher;

import java.util.ArrayList;
import java.util.List;

/**
 * 模拟调用接口返回的数据
 * Created by shucheng on 2020/9/20 15:12
 */
public class DcDataSource {

    // 查询单条数据
    public static <T> ResponseEntity<T> getData(String dcType, Class<T> clazz) {
        ResponseEntity<T> result = new ResponseEntity<>();
        if (DcType.DCTYPE_A001.equals(dcType) && Student.class.equals(clazz)) {
            result.setEntity((T) new Student(1, "张三", 10));
        } else if (DcType.DCTYPE_A003.equals(dcType) && Teacher.class.equals(clazz)) {
            result.setEntity((T) new Teacher(111, "张三丰", 101));
        }
        return result;
    }

    // 查询列表数据
    public static <T> PageInfo<T> getList(String dcType, Class<T> clazz) {
        PageInfo<T> result = new PageInfo<>();
        if (DcType.DCTYPE_A002.equals(dcType) && Student.class.equals(clazz)) {
            List<Student> list = new ArrayList<>();
            list.add(new Student(1, "张三", 10));
            list.add(new Student(2, "李四", 20));
            list.add(new Student(3, "王五", 30));
            result.setList((List<T>) list);
        } else if (DcType.DCTYPE_A004.equals(dcType) && Teacher.class.equals(clazz)) {
            List<Teacher> list = new ArrayList<>();
            list.add(new Teacher(111, "张三丰", 101));
            list.add(new Teacher(222, "张翠山", 102));
            list.add(new Teacher(333, "张无忌", 103));
            result.setList((List<T>) list);
        }
        return result;
    }
}
