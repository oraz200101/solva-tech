package kz.solva.solvatechoraz.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Entity
@Table(name = "limit", schema = "solva")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LimitEntity extends BaseEntity {

    @Column(name = "limit_sum")
    private double limitSum;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "limit")
    private AccountEntity account;


    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now(ZoneOffset.UTC);
    }
}
