package ma.enset.bank_account_service.web;

import ma.enset.bank_account_service.dto.BankAccountRequestDTO;
import ma.enset.bank_account_service.dto.BankAccountResponseDTO;
import ma.enset.bank_account_service.entities.BankAccount;
import ma.enset.bank_account_service.entities.Customer;
import ma.enset.bank_account_service.repository.BankAccountRepository;
import ma.enset.bank_account_service.repository.CustomerRepository;
import ma.enset.bank_account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class BankAccountGraphQlController {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountService accountService;
    @Autowired
    private CustomerRepository customerRepository;
    @QueryMapping
    public List<BankAccount> accountList() {
        return bankAccountRepository.findAll();
    }

    @QueryMapping
    public BankAccount accountById(@Argument String id) {
        return bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(String.format("Bank Account not found with ID %s", id)));
    }
    @MutationMapping
    public BankAccountResponseDTO addAaccount(@Argument BankAccountRequestDTO bankAccount) {
        return accountService.addAccount(bankAccount);
    }
    @MutationMapping
    public BankAccountResponseDTO updateAaccount(@Argument String id,@Argument BankAccountRequestDTO bankAccount) {
        return accountService.updateAccount(id,bankAccount);
    }
    @MutationMapping
    public boolean deleteAaccount(@Argument String id) {
        bankAccountRepository.deleteById(id);
        return true;
    }

    @QueryMapping
    public List<Customer> customers() {
        return customerRepository.findAll();
    }



}
