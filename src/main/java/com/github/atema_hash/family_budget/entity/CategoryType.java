package com.github.atema_hash.family_budget.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "category_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_type")
    private Integer sType;

    @Column(name = "category_name", nullable = false)
    private String categoryName;

    @Column(name = "category_code", nullable = false)
    private String categoryCode;

    @Column(name = "budget_limit")
    private Double budgetLimit;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "category_desc")
    private String categoryDesc;
}
