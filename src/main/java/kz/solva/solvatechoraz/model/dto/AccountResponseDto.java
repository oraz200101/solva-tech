package kz.solva.solvatechoraz.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDto {
    private long accountNumber;
    private double productLimitSum;
    private double serviceLimitSum;
}
