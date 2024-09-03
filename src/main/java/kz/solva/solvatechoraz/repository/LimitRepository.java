package kz.solva.solvatechoraz.repository;

import kz.solva.solvatechoraz.model.entity.LimitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<LimitEntity, Long> {

    @Query("SELECT l FROM LimitEntity l WHERE l.account.accountNumber = :accountNumber")
    Optional<LimitEntity> findByAccountNumber(@Param("accountNumber") long accountNumber);

}
