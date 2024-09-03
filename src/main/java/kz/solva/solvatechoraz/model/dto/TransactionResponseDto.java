package kz.solva.solvatechoraz.model.dto;

import kz.solva.solvatechoraz.model.enums.CurrencyShortName;
import kz.solva.solvatechoraz.model.enums.ExpenseCategory;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class TransactionResponseDto {
    private long id;
    private long accountFrom;
    private long accountTo;
    private double sum;
    private ExpenseCategory expenseCategory;
    private boolean limitExceeded;
    private double limitSum;
    private ZonedDateTime limitDateTime;
    private CurrencyShortName limitCurrencyShortName;
}
