package readinglist;

import org.springframework.data.jpa.repository.JpaRepository;
import readinglist.Book;

import java.util.List;

/**
 * Created by shucheng on 2020/8/19 20:42
 */
public interface ReadingListRepository extends JpaRepository<Book, Long> {

    List<Book> findByReader(String reader);
}
