package kz.solva.solvatechoraz.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TransactionResponseTableDto {
    private List<TransactionResponseDto> transactions;
    private int totalPages;
    private int totalElements;
}
