package kz.solva.solvatechoraz.model.dto;

import kz.solva.solvatechoraz.model.enums.ExpenseCategory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequestDto {
    private long accountNumber;
    private LimitRequestDto serviceLimit;
    private LimitRequestDto productLimit;

    public void setDefaultLimitCategoryNames() {
        serviceLimit.setExpenseCategory(ExpenseCategory.SERVICE);
        productLimit.setExpenseCategory(ExpenseCategory.PRODUCT);
    }
}
