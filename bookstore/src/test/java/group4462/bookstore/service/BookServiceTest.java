package group4462.bookstore.service;

import group4462.bookstore.model.Book;
import group4462.bookstore.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
    /**
     * Тесты для методов
     */
    @Mock
    private BookRepository repository;
    @InjectMocks
    private BookService service;

    /**
     * Тест для метода создания книги
     */
    @Test
    public void createBookTest() {
        //Предпосылки
        Book book = new Book();
        book.setId(1L);
        book.setTitle("title");
        book.setAuthor("author");
        book.setDescription("description");
        book.setQuantity(5);
        book.setPrice(new BigDecimal(10));
        //Вызов
        service.createBook(book);
        //Проверка
        verify(repository).save(book);
    }

    /**
     * Тест для метода поиска книги по id
     */
    @Test
    public void findByIdTest() {
        //Предпосылки
        Book book = new Book();
        book.setId(1L);
        book.setTitle("title");
        book.setAuthor("author");
        book.setDescription("description");
        book.setQuantity(5);
        book.setPrice(new BigDecimal(10));
        when(repository.findById(1L)).thenReturn(Optional.of(book));
        //Вызов
        Optional<Book> book1 = service.findById(1L);
        // Проверка
        verify(repository).findById(1L);
        assertNotNull(book1);
        assertEquals(Optional.of(book), book1);
    }
}