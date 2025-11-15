package ma.enset.bank_account_service.service;

import jakarta.transaction.Transactional;
import ma.enset.bank_account_service.dto.BankAccountRequestDTO;
import ma.enset.bank_account_service.dto.BankAccountResponseDTO;
import ma.enset.bank_account_service.entities.BankAccount;
import ma.enset.bank_account_service.mappers.AccountMapper;
import ma.enset.bank_account_service.repository.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class AccountServiceImp implements AccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public BankAccountResponseDTO addAccount(BankAccountRequestDTO bankAccountDTO) {

        // 1️⃣ Création de l’objet BankAccount à partir du DTO
        BankAccount bankAccount = BankAccount.builder()
                .id(UUID.randomUUID().toString())
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();

        // 2️⃣ Sauvegarde dans la base de données
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        // 3️⃣ Conversion en DTO via le mapper
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(savedBankAccount);

        // 4️⃣ Retour du DTO
        return bankAccountResponseDTO;
    }

    @Override
    public BankAccountResponseDTO updateAccount(String id, BankAccountRequestDTO bankAccountDTO) {

        // 1️⃣ Création de l’objet BankAccount à partir du DTO
        BankAccount bankAccount = BankAccount.builder()
                .id(id)
                .createdAt(new Date())
                .balance(bankAccountDTO.getBalance())
                .type(bankAccountDTO.getType())
                .currency(bankAccountDTO.getCurrency())
                .build();

        // 2️⃣ Sauvegarde dans la base de données
        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        // 3️⃣ Conversion en DTO via le mapper
        BankAccountResponseDTO bankAccountResponseDTO = accountMapper.fromBankAccount(savedBankAccount);

        // 4️⃣ Retour du DTO
        return bankAccountResponseDTO;
    }

}
