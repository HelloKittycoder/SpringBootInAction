package com.kittycoder.book.po;

/**
 * Created by shucheng on 2020/9/11 18:53
 */
public class Book {

    private int id;
    private String bookName;
    private String author;

    public Book() {
    }

    public Book(int id, String bookName, String author) {
        this.id = id;
        this.bookName = bookName;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
