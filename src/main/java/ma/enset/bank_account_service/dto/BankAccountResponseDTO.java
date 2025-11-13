package ma.enset.bank_account_service.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Builder;
import ma.enset.bank_account_service.enums.AccountStatus;
import ma.enset.bank_account_service.enums.AccountType;

import java.util.Date;
@Builder
public class BankAccountResponseDTO {
    private String id;
    private String currency;
    private Date createdAt;
    private Double balance;
    private AccountType type;

}
