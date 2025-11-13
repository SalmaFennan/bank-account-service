package ma.enset.bank_account_service.service;

import jakarta.transaction.Transactional;
import ma.enset.bank_account_service.dto.BankAccountRequestDTO;
import ma.enset.bank_account_service.dto.BankAccountResponseDTO;
import ma.enset.bank_account_service.entities.BankAccount;
import ma.enset.bank_account_service.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
@Service
@Transactional
public class AccountServiceImp implements AccountService{
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {
        BankAccount bankAccount = BankAccount.builder().id(UUID.randomUUID().toString()).createdAt(new Date()).
                balance(bankAccountDTO.getBalance()).type(bankAccountDTO.getType()).currency(bankAccountDTO.getCurrency())
                .build();
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);
        BankAccountResponseDTO bankAccountResponseDTO = BankAccountResponseDTO.builder().id(savedBankAccount.getId())
                .type(savedBankAccount.getType()).createdAt(savedBankAccount.getCreatedAt()).currency(savedBankAccount.getCurrency()).balance(savedBankAccount.getBalance()).build();
        return bankAccountResponseDTO;
    }
}
