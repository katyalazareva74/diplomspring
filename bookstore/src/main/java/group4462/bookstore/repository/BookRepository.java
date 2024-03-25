package group4462.bookstore.repository;

import group4462.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    /**
     * Метод поиска книги по id
     * @param id - номер книги
     * @return возвращает найденную книгу
     */
    Optional<Book> findById(Long id);
}
