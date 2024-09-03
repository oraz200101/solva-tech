package kz.solva.solvatechoraz.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequestDto {
    private long accountNumber;
    private LimitRequestDto limit;
}
