package kz.solva.solvatechoraz.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionSearchFilterDto {
    private String searchField;
    private String searchValue;
}
