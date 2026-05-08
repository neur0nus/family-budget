package ru.java.fambudg.hw;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
	
	public LocalDate date;
	public BigDecimal amount;
	public String currency;
	public Category category;
	public String description;
	
	public Category getCategory() {return this.category;}
	public BigDecimal getAmount() {return this.amount;}
}