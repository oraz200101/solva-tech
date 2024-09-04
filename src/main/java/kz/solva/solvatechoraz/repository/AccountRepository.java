package kz.solva.solvatechoraz.repository;

import kz.solva.solvatechoraz.model.dto.AccountResponseDto;
import kz.solva.solvatechoraz.model.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {

    @Query("SELECT new kz.solva.solvatechoraz.model.dto.AccountResponseDto(a.accountNumber, p.limitSum, s.limitSum) " +
            "FROM AccountEntity a " +
            "LEFT JOIN LimitEntity p ON p.id = a.productLimit.id " +
            "LEFT JOIN LimitEntity s ON s.id = a.productLimit.id " +
            "WHERE a.accountNumber = :accountNumber")
    Optional<AccountResponseDto> findAccountByNumber(@Param("accountNumber") long accountNumber);


    @Query("select (count(a) > 0) from AccountEntity a where a.accountNumber = :accountNumber")
    boolean existsAccountByNumber(@Param("accountNumber") long accountNumber);
}
