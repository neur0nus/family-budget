package budget;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);
    private final List<Transaction> transactions = new CopyOnWriteArrayList<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    // Конструктор добавляет тестовую транзакцию
    public TransactionController() {
        transactions.add(new Transaction(idGenerator.getAndIncrement(),
                "Продукты", new BigDecimal("-500"), "RUB", LocalDate.now()));
        log.debug("Test transaction added");
        log.info("Controller initialized");
    }

    @GetMapping
    public List<Transaction> getAll() {
        log.debug("GET all - {} transactions", transactions.size());
        return transactions;
    }

    @GetMapping("/{id}")
    public Transaction getById(@PathVariable int id) {
        log.debug("GET id={}", id);
        return transactions.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElseThrow(() -> {
                    log.warn("Transaction id={} not found", id);
                    return new RuntimeException("Transaction not found");
                });
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction create(@RequestBody Transaction transaction) {
        transaction.setId(idGenerator.getAndIncrement());
        transactions.add(transaction);
        log.info("Created transaction id={}, description={}",
                transaction.getId(), transaction.getDescription());
        return transaction;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        boolean removed = transactions.removeIf(t -> t.getId() == id);
        if (removed) {
            log.info("Deleted transaction id={}", id);
        } else {
            log.warn("Delete failed - id={} not found", id);
            throw new RuntimeException("Transaction not found");
        }
    }
}
