package readinglist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import readinglist.po.Reader;

/**
 * Created by shucheng on 2020/8/27 22:30
 */
public interface ReaderRepository extends JpaRepository<Reader, String> {
}
