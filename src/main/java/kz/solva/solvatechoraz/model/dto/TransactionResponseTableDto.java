package kz.solva.solvatechoraz.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(staticName = "of")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionResponseTableDto {
    private List<TransactionResponseDto> transactions;
    private long totalPages;
    private long totalElements;
}
