// src/main/java/budget/ConfigReader.java
package budget;

import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
	
    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader().getResourceAsStream("app.properties")) {
            if (input == null) throw new RuntimeException("app.properties not found");
            props.load(input);
            configureLogging();
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config", e);
        }
    }

    private static void configureLogging() {
        System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", props.getProperty("log.level", "INFO"));
        System.setProperty("org.slf4j.simpleLogger.log.budget", props.getProperty("log.level.budget", "DEBUG"));
        System.setProperty("org.slf4j.simpleLogger.showDateTime", props.getProperty("log.showDateTime", "true"));
        System.setProperty("org.slf4j.simpleLogger.dateTimeFormat", props.getProperty("log.dateTimeFormat", "yyyy-MM-dd HH:mm:ss"));
    }

    public static int getPort() {
        return Integer.parseInt(props.getProperty("server.port", "8080"));
    }

    public static String getHost() {
        return props.getProperty("server.host", "0.0.0.0");
    }
    
}