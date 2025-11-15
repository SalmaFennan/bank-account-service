package ma.enset.bank_account_service.web;

import ma.enset.bank_account_service.dto.BankAccountRequestDTO;
import ma.enset.bank_account_service.dto.BankAccountResponseDTO;
import ma.enset.bank_account_service.mappers.AccountMapper;
import ma.enset.bank_account_service.service.AccountService;
import org.springframework.web.bind.annotation.RequestBody;
import ma.enset.bank_account_service.entities.BankAccount;
import ma.enset.bank_account_service.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class AccountRestController {
    private AccountMapper accountMapper;
    private final AccountService accountService;
    private BankAccountRepository bankAccountRepository;
    public AccountRestController(BankAccountRepository bankAccountRepository, AccountService accountService, AccountMapper accountMapper) {
        this.bankAccountRepository = bankAccountRepository;
        this.accountService = accountService;
        this.accountMapper = new AccountMapper();
    }
    @GetMapping("/bankAccount")
    public List<BankAccount> bankAccounts() {
    return bankAccountRepository.findAll();
    }
    @GetMapping("/bankAccounts/{id}")
    public BankAccount bankAccounts(@PathVariable String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Account %s is not found", id)));
    }
    @PostMapping("/bankAccounts")
    public BankAccountResponseDTO save(@RequestBody BankAccountRequestDTO requestDTO) {
        return accountService.addAccount(requestDTO);
    }
    @PutMapping("/bankAccounts/{id}")
    public BankAccount update(@PathVariable String id, @RequestBody BankAccount bankAccount) {
        BankAccount account = bankAccountRepository.findById(id).orElse(null);
       if(bankAccount.getBalance()!=null) account.setBalance(bankAccount.getBalance());
        if(bankAccount.getCreatedAt() != null) account.setCreatedAt(new Date());
       if(bankAccount.getType()!=null) account.setType(bankAccount.getType());
       if(bankAccount.getCurrency()!=null) account.setCurrency(bankAccount.getCurrency());
        return bankAccountRepository.save(account);
    }
    @DeleteMapping("/bankAccounts/{id}")
    public void delete(@PathVariable String id) {
        bankAccountRepository.deleteById(id);
    }

}
