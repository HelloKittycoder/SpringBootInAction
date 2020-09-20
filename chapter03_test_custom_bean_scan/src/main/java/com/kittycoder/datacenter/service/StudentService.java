package com.kittycoder.datacenter.service;

import com.kittycoder.custombeanscan.DcRequest;
import com.kittycoder.datacenter.common.DcType;
import com.kittycoder.datacenter.common.PageInfo;
import com.kittycoder.datacenter.common.ResponseEntity;
import com.kittycoder.datacenter.po.Student;

/**
 * Created by shucheng on 2020/9/20 14:55
 */
public interface StudentService {

    @DcRequest(value = DcType.DCTYPE_A001)
    ResponseEntity<Student> queryStudent();
    @DcRequest(value = DcType.DCTYPE_A002)
    PageInfo<Student> queryStudentList();
}
