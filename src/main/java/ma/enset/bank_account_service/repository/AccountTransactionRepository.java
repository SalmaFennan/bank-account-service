package ma.enset.bank_account_service.repository;

import ma.enset.bank_account_service.entities.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AccountTransactionRepository extends JpaRepository<AccountTransaction,Long> {
    List<AccountTransaction> findByBankAccountId(String accountId);
}