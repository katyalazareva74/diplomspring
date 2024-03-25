package group4462.payment.service;

import group4462.payment.exception.ResourceNotFoundException;
import group4462.payment.model.Account;
import group4462.payment.model.TransferObject;
import group4462.payment.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountService {
    private AccountRepository repository;
    /**
     * Метод поиска всех счетоа
     * @return - возвращает список счетов
     */
    public List<Account> findAllAccount(){
        return repository.findAll();
    }
    /**
     * Метод создания счета
     * @param account - сам счет
     * @return - возвращает созданный счет
     */
    public Account createAccount(Account account){
        return repository.save(account);
    }
    /**
     * Метод поиска счета по имени покупателя
     * @param name - имя покупателя
     * @return - возвращает найденный счет
     * */
    public Optional<Account> findByName(String name){
        return repository.findAccountsByName(name);
    }
    /**
     * Метод поиска счета по id счета
     * @param id - id счета
     * @return - возвращает найденный счет
     * */
    public Optional<Account> findById(Long id){
        return repository.findAccountsById(id);
    }

    /**
     * Метод удаления счета по id
     * @param id - id счета
     */
    public void deleteAccountById(Long id) {
        repository.deleteById(id);
    }

    /**
     * Метод списания денег со счета покупателя за покупку книги
     * @param dataObject - данные о счете покупателя и о счете магазина и цене книги
     * @return - возвращает информацию о списании денег со счета
     */
    @Transactional
    public boolean paymentBook(TransferObject dataObject) throws ResourceNotFoundException {
        Account sender = repository.findAccountsByName(dataObject.getSenderName()).stream().findFirst().orElseThrow(()->new ResourceNotFoundException("Not found account"));
        sender.setAmount(dataObject.getAmount().add(sender.getAmount()));
        Account receiver = repository.findAccountsByName(dataObject.getReceiverName()).stream().findFirst().orElseThrow(()->new ResourceNotFoundException("Not found account"));
        BigDecimal senderAmount = sender.getAmount().subtract(dataObject.getSumma());
        BigDecimal num = new BigDecimal(0);
        if(senderAmount.compareTo(num)<0){
             return false;
        }
        sender.setAmount(senderAmount);
        BigDecimal receiverAmount = receiver.getAmount().add(dataObject.getSumma());
        receiver.setAmount(receiverAmount);
        repository.save(sender);
        repository.save(receiver);
        return true;
    }
}
