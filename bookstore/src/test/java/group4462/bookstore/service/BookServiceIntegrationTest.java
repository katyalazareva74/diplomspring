package group4462.bookstore.service;

import group4462.bookstore.model.Book;
import group4462.bookstore.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class BookServiceIntegrationTest {
    @MockBean
    private BookRepository repository;
    @Autowired
    private BookService service;

    @Test
    public void bookDeleteIntegrationTest() {
        //Предпосылки
        Book book = new Book();
        book.setId(1L);
        book.setTitle("title");
        book.setAuthor("author");
        book.setDescription("description");
        book.setQuantity(5);
        book.setPrice(new BigDecimal(10));
        given(repository.findById(1L)).willReturn(Optional.of(book));
        // Вызов
        service.deleteById(1L);
        // Проверка
        verify(repository).deleteById(1L);
    }

    @Test
    public void bookFindByIdIntegrationTest() {
        // Предпосылки
        Book book = new Book();
        book.setId(1L);
        book.setTitle("title");
        book.setAuthor("author");
        book.setDescription("description");
        book.setQuantity(5);
        book.setPrice(new BigDecimal(10));
        when((Optional<Book>) repository.findById(1L)).thenReturn(Optional.of(book));
        //Вызов
        Optional<Book> book1 = service.findById(1L);
        // Проверка
        verify(repository).findById(1L);
        assertNotNull(book1);
        assertEquals(Optional.of(book), book1);
    }
}
