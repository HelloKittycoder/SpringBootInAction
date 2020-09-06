package readinglist.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import readinglist.ReadingListApplication;
import readinglist.po.Book;

import java.util.Optional;

import static org.junit.Assert.assertEquals;

/**
 * Created by shucheng on 2020/8/31 21:40
 */
@RunWith(SpringRunner.class)
@SpringBootTest
// 下面的写法和直接写@SpringBootTest是等效的（因为目前能推断出ReadingListApplication是主方法入口类）
// 具体推断过程后续再深入看
// @SpringBootTest(classes = ReadingListApplication.class)
public class ReadingListRepositoryTest {

    @Autowired
    private ReadingListRepository repo;

    @Test
    public void saveBook() {
        assertEquals(0, repo.findAll().size());

        Book book = new Book();
        book.setTitle("TITLE");
        book.setDescription("DESCRIPTION");
        book.setAuthor("AUTHOR");
        book.setIsbn("1234567890");
        book.setReader("reader");
        Book saved = repo.save(book);

        Book found = repo.findById(saved.getId()).orElse(null);
        assertEquals(saved.getId(), found.getId());
        assertEquals("TITLE", found.getTitle());
        assertEquals("DESCRIPTION", found.getDescription());
        assertEquals("AUTHOR", found.getAuthor());
        assertEquals("1234567890", found.getIsbn());
        assertEquals("reader", found.getReader());
    }
}
