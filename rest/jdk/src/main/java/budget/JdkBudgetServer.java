package budget;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class JdkBudgetServer {
    private static final Logger log = Logger.getLogger(JdkBudgetServer.class.getName());
    private static final List<Transaction> transactions = new CopyOnWriteArrayList<>();
    private static final AtomicInteger idGenerator = new AtomicInteger(1);

    public static void main(String[] args) throws IOException {
        log.info("Starting JDK HTTP Budget Server...");

        // Добавляем тестовую транзакцию
        transactions.add(new Transaction(idGenerator.getAndIncrement(),
                "Продукты", new java.math.BigDecimal("-500"), "RUB", java.time.LocalDate.now()));
        log.fine("Test transaction added");

        int port = ConfigReader.getPort();
        String host = ConfigReader.getHost();
        int backlog = ConfigReader.getBacklog();

        HttpServer server = HttpServer.create(new InetSocketAddress(host, port), backlog);
        server.createContext("/api/transactions", new TransactionHandler());
        server.setExecutor(null); // используем стандартный executor
        server.start();

        log.info("Server started on http://" + host + ":" + port);
    }

    static class TransactionHandler implements HttpHandler {
        private final com.google.gson.Gson gson = GsonUtil.getGson();
        private final Logger log = Logger.getLogger(TransactionHandler.class.getName());

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();
            log.fine("Request: " + method + " " + path);

            try {
                if ("GET".equals(method)) {
                    if (path.equals("/api/transactions")) {
                        handleGetAll(exchange);
                    } else if (path.matches("/api/transactions/\\d+")) {
                        int id = Integer.parseInt(path.substring("/api/transactions/".length()));
                        handleGetOne(exchange, id);
                    } else {
                        sendResponse(exchange, 404, "{\"error\":\"Not found\"}");
                    }
                } else if ("POST".equals(method)) {
                    handlePost(exchange);
                } else if ("DELETE".equals(method) && path.matches("/api/transactions/\\d+")) {
                    int id = Integer.parseInt(path.substring("/api/transactions/".length()));
                    handleDelete(exchange, id);
                } else {
                    sendResponse(exchange, 405, "{\"error\":\"Method not allowed\"}");
                }
            } catch (Exception e) {
                log.severe("Error processing request: " + e.getMessage());
                sendResponse(exchange, 500, "{\"error\":\"" + e.getMessage() + "\"}");
            }
        }

        private void handleGetAll(HttpExchange exchange) throws IOException {
            String json = gson.toJson(transactions);
            log.info("GET all – returning " + transactions.size() + " transactions");
            sendResponse(exchange, 200, json);
        }

        private void handleGetOne(HttpExchange exchange, int id) throws IOException {
            Transaction tx = transactions.stream()
                    .filter(t -> t.getId() == id)
                    .findFirst()
                    .orElse(null);
            if (tx != null) {
                log.info("GET transaction id=" + id);
                sendResponse(exchange, 200, gson.toJson(tx));
            } else {
                log.warning("GET transaction id=" + id + " not found");
                sendResponse(exchange, 404, "{\"error\":\"Transaction not found\"}");
            }
        }

        private void handlePost(HttpExchange exchange) throws IOException {
            // Читаем тело запроса как строку
            String body = new BufferedReader(new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8))
            		.lines()
            		.collect(Collectors.joining("\n"));
            Transaction newTx = gson.fromJson(body, Transaction.class);
            newTx.setId(idGenerator.getAndIncrement());
            transactions.add(newTx);
            String response = gson.toJson(newTx);
            log.info("POST created transaction id=" + newTx.getId());
            sendResponse(exchange, 201, response);
        }

        private void handleDelete(HttpExchange exchange, int id) throws IOException {
            boolean removed = transactions.removeIf(t -> t.getId() == id);
            if (removed) {
                log.info("DELETE transaction id=" + id);
                sendResponse(exchange, 204, ""); // No content
            } else {
                log.warning("DELETE transaction id=" + id + " not found");
                sendResponse(exchange, 404, "{\"error\":\"Transaction not found\"}");
            }
        }

        private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
            byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(statusCode, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
        }
    }
}
