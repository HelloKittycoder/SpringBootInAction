package com.kittycoder.datacenter.common;

/**
 * Created by shucheng on 2020/9/20 14:58
 */
public class ResponseEntity<T> {

    private T entity;

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
