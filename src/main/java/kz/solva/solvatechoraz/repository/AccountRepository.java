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

    @Query("SELECT new kz.solva.solvatechoraz.model.dto.AccountResponseDto(a.accountNumber, l.limitSum) " +
            "FROM AccountEntity a JOIN a.limit l " +
            "WHERE a.accountNumber = :accountNumber")
    Optional<AccountResponseDto> findAccountByNumber(@Param("accountNumber") long accountNumber);
}
