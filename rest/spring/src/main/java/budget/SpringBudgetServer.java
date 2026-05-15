package budget;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBudgetServer {
    private static final Logger log = LoggerFactory.getLogger(SpringBudgetServer.class);

    public static void main(String[] args) {
        log.info("Starting Spring Boot Budget Server...");
        SpringApplication.run(SpringBudgetServer.class, args);
        log.info("Server started successfully");
    }
}
