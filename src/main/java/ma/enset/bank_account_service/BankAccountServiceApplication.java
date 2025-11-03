package ma.enset.bank_account_service;

import ma.enset.bank_account_service.entities.BankAccount;
import ma.enset.bank_account_service.enums.AccountStatus;
import ma.enset.bank_account_service.enums.AccountType;
import ma.enset.bank_account_service.repository.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository) {
		return args -> {
			System.out.println("=== Début de l'insertion des comptes ===");

			for (int i = 0; i < 10; i++) {
				BankAccount bankAccount = BankAccount.builder()
						.id(UUID.randomUUID().toString())
						.type(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
						.balance(10000 + Math.random() * 90000)
						.createdAt(new Date())
						.currency("MAD")
						.status(AccountStatus.CREATED) // N'oubliez pas de définir le status !
						.customerId(1L + (long)(Math.random() * 10))
						.build();

				bankAccountRepository.save(bankAccount);
				System.out.println("Compte sauvegardé : " + bankAccount.getId());
			}

			long count = bankAccountRepository.count();
			System.out.println("=== Total des comptes en base : " + count + " ===");
		};
	}
}
