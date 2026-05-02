package ru.java.fambudg.constraints;

import java.time.LocalDate;

public class DataConstraints {
    private final LocalDate dateFrom;
    private final LocalDate dateTo;
    private final String category;

    
    public DataConstraints(LocalDate dateFrom, LocalDate dateTo, String category) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.category = category;
    }

    public LocalDate getDateFrom() { return dateFrom; }
    public LocalDate getDateTo() { return dateTo; }
    public String getCategory() { return category; }
}
