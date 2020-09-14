package com.kittycoder.book.repository;

import com.kittycoder.book.po.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by shucheng on 2020/9/14 0:20
 */
@Repository
public class BookRepositoryImpl implements BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Book> selectBookList() {
        return jdbcTemplate.query("select * from t_book",
                (rs, rowNum) -> new Book(rs.getInt("l_id"), rs.getString("vc_book_name"), rs.getString("vc_author"))
        );
    }

    @Override
    public void insertBook(Book book) {
        jdbcTemplate.update("insert into t_book(l_id, vc_book_name, vc_author)"
                + " values(?, ?, ?)", book.getId(), book.getBookName(), book.getAuthor());
    }

    @Override
    public void updateBook(Book book) {
        jdbcTemplate.update("update t_book set vc_book_name = ?, vc_author = ? where l_id = ?",
                book.getBookName(), book.getAuthor(), book.getId());
    }
}
