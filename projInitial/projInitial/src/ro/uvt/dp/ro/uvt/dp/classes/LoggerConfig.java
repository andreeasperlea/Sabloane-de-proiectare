package ro.uvt.dp.classes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerConfig {

    private static LoggerConfig instance;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    private LoggerConfig() {}

    public static synchronized LoggerConfig getInstance() {
        if (instance == null) {
            instance = new LoggerConfig();
        }
        return instance;
    }

    public void logInfo(String message) {
        System.out.println("[INFO]  " + now() + "  " + message);
    }

    public void logWarning(String message) {
        System.out.println("[WARN]  " + now() + "  " + message);
    }

    public void logError(String message, Exception e) {
        System.out.println("[ERROR] " + now() + "  " + message + " | Exception: " + e.getMessage());
    }

    private String now() {
        return LocalDateTime.now().format(formatter);
    }
}
