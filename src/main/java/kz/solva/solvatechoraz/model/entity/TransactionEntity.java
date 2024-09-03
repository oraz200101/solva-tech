package kz.solva.solvatechoraz.model.entity;

import jakarta.persistence.*;
import kz.solva.solvatechoraz.model.enums.CurrencyShortName;
import kz.solva.solvatechoraz.model.enums.ExpenseCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transaction", schema = "solva")
public class TransactionEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_from", referencedColumnName = "account_number")
    private AccountEntity accountFrom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_to", referencedColumnName = "account_number")
    private AccountEntity accountTo;

    @Column(name = "sum")
    private double sum;

    @Column(name = "currency_short_name", nullable = false)
    private CurrencyShortName currencyShortName;

    @Column(name = "expense_category", nullable = false)
    private ExpenseCategory expenseCategory;

    @Column(name = "limit_exceeded")
    private boolean limitExceeded;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

}
