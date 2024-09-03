package kz.solva.solvatechoraz.model.dto;

import kz.solva.solvatechoraz.model.enums.CurrencyShortName;
import kz.solva.solvatechoraz.model.enums.ExpenseCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionRequestDto {
    private long accountFrom;
    private long accountTo;
    private double sum;
    private ExpenseCategory expenseCategory;
    private CurrencyShortName currencyShortName;
}
