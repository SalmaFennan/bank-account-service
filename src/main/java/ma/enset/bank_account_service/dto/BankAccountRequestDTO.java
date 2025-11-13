package ma.enset.bank_account_service.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.bank_account_service.enums.AccountStatus;
import ma.enset.bank_account_service.enums.AccountType;

import java.util.Date;
@Data @NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankAccountRequestDTO {

    private String currency;
    private Double balance;
    private AccountType type;

}
