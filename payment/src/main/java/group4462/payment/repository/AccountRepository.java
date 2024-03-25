package group4462.payment.repository;

import group4462.payment.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    /**
     * Метод поиска счета по имени покупателя
     * @param name -имя покупателя
     * @return - возвращает найденный счет
     */
    Optional<Account> findAccountsByName(String name);

    /**
     * Метод поиска счета пр его id
     * @param id - id счета
     * @return - возвращает найденный счет
     */
    Optional<Account> findAccountsById(Long id);
}
