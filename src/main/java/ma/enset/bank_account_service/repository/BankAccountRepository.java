package ma.enset.bank_account_service.repository;

import ma.enset.bank_account_service.entities.BankAccount;
import ma.enset.bank_account_service.enums.AccountStatus;
import ma.enset.bank_account_service.enums.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BankAccountRepository extends JpaRepository<BankAccount,String> {
    List<BankAccount> findByType(AccountType type);
    List<BankAccount> findByStatus(AccountStatus status);
    @Query("select " +
            "new ma.enset.bank_account_service.repository.AccountStatus(" +
            "count(ba)," +
            "sum(ba.balance), " +
            "avg(ba.balance), " +
            "min(ba.balance), " +
            "max(ba.balance)) from BankAccount ba")
    AccountStatus getBankStats();
}