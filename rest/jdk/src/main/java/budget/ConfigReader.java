package budget;

import java.io.InputStream;
import java.util.Properties;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ConfigReader {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = ConfigReader.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            if (input == null)
                throw new RuntimeException("app.properties not found in classpath");
            props.load(input);
            configureLogging();
        } catch (Exception e) {
            System.err.println("Failed to load config: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static void configureLogging() {
        Logger root = Logger.getLogger("");
        root.setLevel(Level.parse(props.getProperty("log.level", "INFO")));
        // Удаляем стандартные обработчики, чтобы применить свой формат
        for (var h : root.getHandlers()) {
            root.removeHandler(h);
        }
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        handler.setFormatter(new SimpleFormatter() {
            @Override
            public synchronized String format(java.util.logging.LogRecord record) {
                return String.format("%1$tY-%1$tm-%1$td %1$tH:%1$tM:%1$tS %2$s: %3$s%n",
                        record.getMillis(), record.getLevel().getName(), record.getMessage());
            }
        });
        root.addHandler(handler);

        Logger.getLogger("budget").setLevel(
                Level.parse(props.getProperty("log.level.budget", "DEBUG")));
        Logger.getLogger("com.sun.net.httpserver").setLevel(
                Level.parse(props.getProperty("log.level.com.sun.net.httpserver", "WARNING")));
    }

    public static int getPort() {
        return Integer.parseInt(props.getProperty("server.port", "8080"));
    }

    public static String getHost() {
        return props.getProperty("server.host", "0.0.0.0");
    }

    public static int getBacklog() {
        return Integer.parseInt(props.getProperty("server.backlog", "50"));
    }
}
