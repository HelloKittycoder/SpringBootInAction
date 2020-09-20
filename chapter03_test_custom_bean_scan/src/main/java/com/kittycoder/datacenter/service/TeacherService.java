package com.kittycoder.datacenter.service;

import com.kittycoder.custombeanscan.DcRequest;
import com.kittycoder.datacenter.common.DcType;
import com.kittycoder.datacenter.common.PageInfo;
import com.kittycoder.datacenter.common.ResponseEntity;
import com.kittycoder.datacenter.po.Teacher;

/**
 * Created by shucheng on 2020/9/20 15:16
 */
public interface TeacherService {

    @DcRequest(value = DcType.DCTYPE_A003)
    ResponseEntity<Teacher> queryTeacher();
    @DcRequest(value = DcType.DCTYPE_A004)
    PageInfo<Teacher> queryTeacherList();
}
