package kz.solva.solvatechoraz.repository;

import kz.solva.solvatechoraz.model.entity.LimitEntity;
import kz.solva.solvatechoraz.model.enums.ExpenseCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface LimitRepository extends JpaRepository<LimitEntity, Long> {

    @Query("SELECT l FROM LimitEntity l WHERE l.account.accountNumber = :accountNumber AND l.expenseCategory = :expenseCategory")
    Optional<LimitEntity> findByAccountNumberAndExpenseCategory(@Param("accountNumber") long accountNumber,
                                                                @Param("expenseCategory") ExpenseCategory expenseCategory);

    @Query("SELECT l FROM LimitEntity l WHERE l.account.accountNumber IN :accountNumbers")
    List<LimitEntity> findAllByAccountNumberIn(@Param("accountNumbers") Set<Long> accountNumbers);

}
