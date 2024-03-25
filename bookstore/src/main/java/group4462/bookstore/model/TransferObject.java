package group4462.bookstore.model;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
@Component
@Data
public class TransferObject {
    private String senderName;
    private String receiverName;
    private BigDecimal amount;
    private BigDecimal summa;
}
