package group4462.bookstore.model;

import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
@Data
public class Order {
    private Long bookId;
    private int quantityBooks;
    private String accountName;
    private BigDecimal amount;
}
