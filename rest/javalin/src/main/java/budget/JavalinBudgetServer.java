package budget;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.javalin.Javalin;

public class JavalinBudgetServer {
    private static final Logger log = LoggerFactory.getLogger(JavalinBudgetServer.class);
    private static final List<Transaction> transactions = new CopyOnWriteArrayList<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    public static void main(String[] args) {
        log.info("Starting Javalin Budget Server...");
        transactions.add(new Transaction(idGenerator.getAndIncrement(), "Продукты", new BigDecimal("-500"), "RUB", LocalDate.now()));

        int port = ConfigReader.getPort();
        String host = ConfigReader.getHost();

        Javalin app = Javalin.create(config -> config.jsonMapper(GsonUtil.jsonMapper)).start(host, port);

        log.info("Server started on http://{}:{}", host, port);

        app.get("/api/transactions", ctx -> {
            log.debug("GET all - {} transactions", transactions.size());
            ctx.json(transactions);
        });

        app.post("/api/transactions", ctx -> {
            Transaction newTx = ctx.bodyAsClass(Transaction.class);
            newTx.setId(idGenerator.getAndIncrement());
            transactions.add(newTx);
            log.info("Created transaction id={}, desc={}", newTx.getId(), newTx.getDescription());
            ctx.status(201).json(newTx);
        });

        app.get("/api/transactions/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            log.debug("GET transaction id={}", id);
            Transaction tx = transactions.stream()
                    .filter(t -> t.getId() == id)
                    .findFirst()
                    .orElse(null);
            if (tx != null) {
                ctx.json(tx);
            } else {
                log.warn("Transaction {} not found", id);
                ctx.status(404).result("{\"error\":\"Not found\"}");
            }
        });

        app.delete("/api/transactions/{id}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id"));
            boolean removed = transactions.removeIf(t -> t.getId() == id);
            if (removed) {
                log.info("Deleted transaction id={}", id);
                ctx.status(204);
            } else {
                log.warn("Delete failed - id={} not found", id);
                ctx.status(404).result("{\"error\":\"Not found\"}");
            }
        });
        
    }
}
