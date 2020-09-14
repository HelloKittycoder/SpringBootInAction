package com.kittycoder.book.repository;

import com.kittycoder.book.po.Book;

import java.util.List;

/**
 * Created by shucheng on 2020/9/14 0:20
 */
public interface BookRepository {

    List<Book> selectBookList();
    void insertBook(Book book);
    void updateBook(Book book);
}
