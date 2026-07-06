package com.github.atema_hash.family_budget.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TransactionDTO {

    private String name;
    private Double amount;
    private String currency;
    private LocalDate operationDate;
    private String description;
    private Integer categoryTypeId;
}