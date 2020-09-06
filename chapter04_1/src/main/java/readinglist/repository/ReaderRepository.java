package readinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import readinglist.po.Reader;

/**
 * Created by shucheng on 2020/9/1 9:35
 */
public interface ReaderRepository extends JpaRepository<Reader, String> {
}
