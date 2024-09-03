package kz.solva.solvatechoraz.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "account", schema = "solva")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountEntity extends BaseEntity {

    @Column(name = "account_number", nullable = false, unique = true)
    private long accountNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "limit_id")
    private LimitEntity limit;

}
