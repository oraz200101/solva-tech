package kz.solva.solvatechoraz.model.dto;

import kz.solva.solvatechoraz.model.enums.SortType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionSortFilter {
    private String sortField;
    private SortType sortType;
}
