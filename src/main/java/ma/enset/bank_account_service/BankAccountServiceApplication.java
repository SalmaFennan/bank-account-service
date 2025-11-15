package ma.enset.bank_account_service;

import ma.enset.bank_account_service.entities.BankAccount;
import ma.enset.bank_account_service.entities.Customer;
import ma.enset.bank_account_service.enums.AccountStatus;
import ma.enset.bank_account_service.enums.AccountType;
import ma.enset.bank_account_service.repository.BankAccountRepository;
import ma.enset.bank_account_service.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository) {
		return args -> {

			// Ajouter quelques clients
			Stream.of("Salma", "Anass", "Hanae").forEach(c -> {
				Customer customer = Customer.builder()
						.name(c)
						.build();
				customerRepository.save(customer);
			});

			// Pour chaque client, créer des comptes
			customerRepository.findAll().forEach(customer -> {
				System.out.println("=== Début de l'insertion des comptes pour " + customer.getName() + " ===");

				for (int i = 0; i < 10; i++) {
					BankAccount bankAccount = BankAccount.builder()
							.id(UUID.randomUUID().toString())
							.type(Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT)
							.balance(10000 + Math.random() * 90000)
							.createdAt(new Date())
							.customerId(customer.getId())   // ✔ correction ici
							.currency("MAD")
							.status(AccountStatus.CREATED)
							.build();

					bankAccountRepository.save(bankAccount);
					System.out.println("Compte sauvegardé : " + bankAccount.getId());
				}
			}); // ← ICI : parenthèse fermée correctement ✔️

			long count = bankAccountRepository.count();
			System.out.println("=== Total des comptes en base : " + count + " ===");
		};
	}
}
