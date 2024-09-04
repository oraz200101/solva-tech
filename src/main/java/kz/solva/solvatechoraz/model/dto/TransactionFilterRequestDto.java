package kz.solva.solvatechoraz.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TransactionFilterRequestDto {
    private long accountNumber;
    private TransactionSearchFilterDto searchFilter;
    private List<TransactionSortFilter> sortFilter;
    private PageRequestDto page;
}
