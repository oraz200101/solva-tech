package kz.solva.solvatechoraz.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "limit")
@AllArgsConstructor
@NoArgsConstructor
public class LimitEntity extends BaseEntity {

    @Column(name = "limit")
    private double limit;

    @OneToOne(mappedBy = "limit")
    private AccountEntity account;

}
