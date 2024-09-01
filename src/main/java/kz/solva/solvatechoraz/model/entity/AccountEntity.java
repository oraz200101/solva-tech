package kz.solva.solvatechoraz.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "account")
public class AccountEntity extends BaseEntity {

    @Column(name = "account_number", nullable = false)
    private long accountNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "limit_id")
    private LimitEntity limit;

}
