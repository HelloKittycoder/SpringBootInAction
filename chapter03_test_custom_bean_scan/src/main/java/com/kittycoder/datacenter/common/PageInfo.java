package com.kittycoder.datacenter.common;

import java.util.List;

/**
 * Created by shucheng on 2020/9/20 15:00
 */
public class PageInfo<T> {

    private List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
