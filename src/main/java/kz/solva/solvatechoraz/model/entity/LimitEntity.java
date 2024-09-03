package kz.solva.solvatechoraz.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "limit", schema = "solva")
@AllArgsConstructor
@NoArgsConstructor
public class LimitEntity extends BaseEntity {

    @Column(name = "limit_sum")
    private double limitSum;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "limit")
    private AccountEntity account;


}
