package group4462.payment.controller;

import group4462.payment.exception.ResourceNotFoundException;
import group4462.payment.model.Account;
import group4462.payment.model.TransferObject;
import group4462.payment.service.AccountService;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/accounts")
public class AccountController {
    private AccountService service;
    private  final Counter addNewAccount = Metrics.counter("add_account_counter");

    /**
     * Метод оплаты книги
     * @param dataObject -данные для покупки книги
     * @return возвращает информацию об оплате книги
     * @throws ResourceNotFoundException
     */
    @PostMapping("/payment")
    public boolean paymentBook(@RequestBody TransferObject dataObject) throws ResourceNotFoundException {
        Optional<Account> sender = service.findByName(dataObject.getSenderName());
        if(!sender.isPresent()){
            Account account = new Account();
            account.setName(dataObject.getSenderName());
            account.setAmount(new BigDecimal(0));
            service.createAccount(account);
            addNewAccount.increment();
        }
        Optional<Account> receiver = service.findByName(dataObject.getReceiverName());
        if(!receiver.isPresent()){
            Account account = new Account();
            account.setName(dataObject.getReceiverName());
            account.setAmount(new BigDecimal(0));
            service.createAccount(account);
        }
        if(service.paymentBook(dataObject)){
            return true;
        }
        return false;
    }
}
