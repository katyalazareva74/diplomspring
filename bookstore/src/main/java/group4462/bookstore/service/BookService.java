package group4462.bookstore.service;

import group4462.bookstore.model.Book;
import group4462.bookstore.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;
    /**
     * Метод поиска всех книг
     * @return - возвращает список книг
     */
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }
    /**
     * Метод поиска книги по id
     * @param id - номер книги
     * @return - возвращает найденную книгу
     * */

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }
    /**
     * Метод добавления книги
     * @param book - сама книга
     * @return - возвращает добавленную книгу
     */
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Метод удаления книги по id
     * @param id - номер книги
     */

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

}
