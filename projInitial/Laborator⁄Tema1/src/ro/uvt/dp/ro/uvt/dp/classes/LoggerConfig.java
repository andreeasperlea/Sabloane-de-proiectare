package ro.uvt.dp.classes;

import java.io.IOException;
import java.util.logging.*;

public class LoggerConfig {
    private static final Logger logger = Logger.getLogger("BankLogger");
    private static boolean isEnabled = false;

    static {
        try {
            FileHandler fileHandler = new FileHandler("bank_debug.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private LoggerConfig() {} 

    public static Logger getLogger() {
        return logger;
    }

    public static void enableLogging() {
        isEnabled = true;
        logger.info("Logging ENABLED");
    }

    public static void disableLogging() {
        logger.info("Logging DISABLED");
        isEnabled = false;
    }

    public static void logInfo(String message) {
        if (isEnabled) {
            logger.info(message);
        }
    }

    public static void logWarning(String message) {
        if (isEnabled) {
            logger.warning(message);
        }
    }

    public static void logError(String message, Throwable t) {
        if (isEnabled) {
            logger.log(Level.SEVERE, message, t);
        }
    }
}
