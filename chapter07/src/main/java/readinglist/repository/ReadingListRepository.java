package readinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import readinglist.po.Book;
import readinglist.po.Reader;

import java.util.List;

/**
 * Created by shucheng on 2020/9/1 9:13
 */
public interface ReadingListRepository extends JpaRepository<Book, Long> {

    List<Book> findByReader(Reader reader);
}
