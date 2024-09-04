package kz.solva.solvatechoraz.model.dto;

import kz.solva.solvatechoraz.model.enums.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LimitRequestDto {
    private ExpenseCategory expenseCategory;
    private double limitSum;
}
