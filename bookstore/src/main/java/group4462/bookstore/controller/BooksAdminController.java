package group4462.bookstore.controller;

import group4462.bookstore.exeption.ResourceNotFoundException;
import group4462.bookstore.model.Book;
import group4462.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/books")
public class BooksAdminController {
    private final BookService service;

    /**
     * Запрос возвращает список книг
     *
     * @return - возвращает список книг
     */
    @GetMapping("/admin")
    public String getAllBooks(Model model) {
        List<Book> books = service.getAllBook();
        model.addAttribute("books", books);
        return "books-admin";
    }

    /**
     * Метод для ввода данных о книге
     *
     * @param book - данные книги
     * @return - возвращает книгу
     */
    @GetMapping("/book-create")
    public String createBookForm(Book book) {
        return "book-create";
    }

    /**
     * Метод для записи книги в общий список
     *
     * @param book - данные книги
     * @return - возвращает пополненный список книг
     */
    @PostMapping("/book-create")
    public String addBook(Book book) {
        service.createBook(book);
        return "confirmation";
    }

    /**
     * Метод для поиска книги по id в списке
     *
     * @param id -  id книги
     * @param model модель
     * @return возвращает найденнцю книгу
     * @throws ResourceNotFoundException - исключение выбрасывается, когда книга не найдена
     */
    @GetMapping("/book-update/{id}")
    public String upDateBook(@PathVariable("id") Long id, Model model) throws ResourceNotFoundException {
        Optional<Book> book = Optional.ofNullable(service.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found book " + id)));
        model.addAttribute("book", book);
        return "book-update";
    }

    /**
     * Метод для редактирования найденной книги
     *
     * @param book - книга
     * @return - возвращает список книг вместе с отредактированной книгой
     */
    @PostMapping("/book-update")
    public String upDate(Book book) {
        service.createBook(book);
        return "confirmation";
    }

    /**
     * Метод для поиска книги по id в списке
     *
     * @param id    номер книги
     * @param model модель
     * @return возвращает найденнцю книгу
     * @throws ResourceNotFoundException - исключение выбрасывается, когда книга не найдена
     */
    @GetMapping("/book-delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) throws ResourceNotFoundException {
        Optional<Book> book = Optional.ofNullable(service.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found book " + id)));
        model.addAttribute("book", book);
        service.deleteById(id);
        return "confirmation";
    }
}
