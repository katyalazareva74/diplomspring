package group4462.bookstore.controller;


import group4462.bookstore.model.TransferObject;
import group4462.bookstore.exeption.ResourceNotFoundException;
import group4462.bookstore.model.Book;
import group4462.bookstore.model.Order;
import group4462.bookstore.proxy.PaymentProxy;
import group4462.bookstore.service.BookService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@Controller
@AllArgsConstructor
public class BooksUserController {
    private final BookService service;
    private final PaymentProxy paymentProxy;
    private final TransferObject dataObject;
    private  final Counter allRequestBooks = Metrics.counter("all-request_book_counter");
    /**
     * Запрос возвращает список книг
     * @return - возвращает список книг
     */
    @GetMapping("/books")
    public String getAllBooks(Model model){
        List<Book> books = service.getAllBook();
        model.addAttribute("books", books);
        return "books";
    }

    /**
     * Метод заполнения формы для покупки книги
     * @param id - шв книги
     * @param order - форма для покупки книги
     * @return - возвращает заполненную форму
     * @throws ResourceNotFoundException
     */
    @GetMapping("/accounts/payment/{id}")
    public String buyBook(@PathVariable("id") Long id, Order order) throws ResourceNotFoundException {
        allRequestBooks.increment();
        order.setBookId(id);
        return "payment";
    }

    /**
     * Метод оплаты книги
     * @param order - форма для покупки книги
     * @return - возвращает информацию о покупке книги
     * @throws ResourceNotFoundException
     */
    @PostMapping("/accounts/payment")
    public String paymentBook(Order order) throws ResourceNotFoundException {
        Book book = service.findById(order.getBookId()).orElseThrow(() -> new ResourceNotFoundException("Not found book"));
       if(order.getAmount()==null){
            System.out.println(2);
            order.setAmount(new BigDecimal(0));
        }
        if((book.getQuantity()<order.getQuantityBooks())||(order.getQuantityBooks()<=0)||(order.getAccountName().isEmpty())){
            return"order-error";
        }
        book.setQuantity(book.getQuantity() - order.getQuantityBooks());
        service.createBook(book);
        BigDecimal summa = book.getPrice().multiply(BigDecimal.valueOf(order.getQuantityBooks()));
        dataObject.setSenderName(order.getAccountName());
        dataObject.setReceiverName("bookstore");
        dataObject.setAmount(order.getAmount());
        dataObject.setSumma(summa);
        if(!paymentProxy.paymentBook(dataObject)){
            book.setQuantity(book.getQuantity() + order.getQuantityBooks());
            service.createBook(book);
            return "payment-error";
        }
        return "payment-success";
    }

}
