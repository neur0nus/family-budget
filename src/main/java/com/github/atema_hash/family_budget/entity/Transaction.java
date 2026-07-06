package com.github.atema_hash.family_budget.entity;

import com.github.atema_hash.family_budget.entity.CategoryType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name="subj")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_id")
    private Long id;

    @Column(name = "s_name")
    private String name;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "currency", length = 3)
    private String currency;

    @Column(name = "operation_date")
    private LocalDate operationDate;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "s_type", nullable = false)
    private CategoryType categoryType;
}
