package nibm.kd.hdse242.lmscw.repositories;

import nibm.kd.hdse242.lmscw.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
