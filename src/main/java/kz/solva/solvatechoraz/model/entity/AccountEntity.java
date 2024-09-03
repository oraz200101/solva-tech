package kz.solva.solvatechoraz.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "account", schema = "solva")
public class AccountEntity extends BaseEntity {

    @Column(name = "account_number", nullable = false, unique = true)
    private long accountNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "limit_id")
    private LimitEntity limit;

}
