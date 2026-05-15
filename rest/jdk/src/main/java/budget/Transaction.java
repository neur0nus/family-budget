package budget;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction {
    private int id;
    private String description;
    private BigDecimal amount;
    private String currency;
    private LocalDate date;

    public Transaction() {}

    public Transaction(int id, String description, BigDecimal amount, String currency, LocalDate date) {
        this.id = id;
        this.description = description;
        this.amount = amount;
        this.currency = currency;
        this.date = date;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }
}
