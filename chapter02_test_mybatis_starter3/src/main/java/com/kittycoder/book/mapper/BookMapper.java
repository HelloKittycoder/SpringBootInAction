package com.kittycoder.book.mapper;

import com.kittycoder.book.po.Book;

import java.util.List;

/**
 * Created by shucheng on 2020/9/11 18:54
 */
public interface BookMapper {

    List<Book> selectBookList();
    void insertBook(Book book);
    void updateBook(Book book);
}
