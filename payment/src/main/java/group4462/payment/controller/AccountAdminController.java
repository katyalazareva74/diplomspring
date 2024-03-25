package group4462.payment.controller;

import group4462.payment.exception.ResourceNotFoundException;
import group4462.payment.model.Account;
import group4462.payment.service.AccountService;
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
@RequestMapping("/admin")
public class AccountAdminController {
    private AccountService service;
        /**
     * Запрос возвращает список счетов
     *
     * @return - возвращает список счетов
     */
    @GetMapping
    public String getAllAccounts(Model model) {
        List<Account> accounts = service.findAllAccount();
        model.addAttribute("accounts", accounts);
        return "account-admin";
    }

    /**
     * Метод для ввода данных о счете
     *
     * @param account - данные счета
     * @return - возвращает счет
     */
    @GetMapping("/account-create")
    public String createAccount(Account account) {
        return "account-create";
    }

    /**
     * Метод для записи счета в общий список
     *
     * @param account - данные счета
     * @return - возвращает пополненный список счетов
     */
    @PostMapping("/account-create")
    public String addAccount(Account account) {
        service.createAccount(account);
        return "confirmation";
    }
    /**
     * Метод для поиска счета по его id
     *
     * @param id -  id счета покупателя
     * @param model модель
     * @return - возвращает найденный счет
     * @throws ResourceNotFoundException - исключение выбрасывается, когда счет не найден
     */
    @GetMapping("/account-update/{id}")
    public String upDateBook(@PathVariable("id") Long id, Model model) throws ResourceNotFoundException {
        Optional<Account> account = Optional.ofNullable(service.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found account " + id)));
        model.addAttribute("account", account);
        return "account-update";
    }

    /**
     * Метод для редактирования найденного счета
     *
     * @param account - счет
     * @return - возвращает список счетов вместе с отредактированным счетом
     */
    @PostMapping("/account-update")
    public String upDate(Account account) {
        service.createAccount(account);
        return "confirmation";
    }
    /**
     * Метод для поиска счета по id в списке
     *
     * @param id  - id счета
     * @param model модель
     * @return возвращает найденный счет
     * @throws ResourceNotFoundException - исключение выбрасывается, когда счет не найдена
     */
    @GetMapping("/account-delete/{id}")
    public String deleteBook(@PathVariable("id") Long id, Model model) throws ResourceNotFoundException {
        Optional<Account> account = Optional.ofNullable(service.findById(id).orElseThrow(() -> new ResourceNotFoundException("Not found account " + id)));
        model.addAttribute("account", account);
        service.deleteAccountById(id);
        return "confirmation";
    }
}
