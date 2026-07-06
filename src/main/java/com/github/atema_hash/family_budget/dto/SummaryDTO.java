package com.github.atema_hash.family_budget.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SummaryDTO {
    private String categoryName;
    private Double totalAmount;
}
