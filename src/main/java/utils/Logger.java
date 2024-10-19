package utils;

import ecosystem.Animal;
import ecosystem.EcoSystem;
import ecosystem.Environment;
import ecosystem.Plant;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Logger {
    private final String logFile;
    private LogLevel currentLogLevel = LogLevel.INFO;

    public enum LogLevel {
        DEBUG, INFO, WARN, ERROR
    }

    public Logger(String logFile) {
        this.logFile = generateLogFileName(logFile);
    }

    private String generateLogFileName(String baseName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String timestamp = dateFormat.format(new Date());
        return baseName + "_" + timestamp + ".log";
    }

    public void setLogLevel(LogLevel logLevel) {
        this.currentLogLevel = logLevel;
    }

    public void debug(String message) {
        if (currentLogLevel.ordinal() <= LogLevel.DEBUG.ordinal()) {
            log("DEBUG: " + message);
        }
    }

    public void info(String message) {
        if (currentLogLevel.ordinal() <= LogLevel.INFO.ordinal()) {
            log("INFO: " + message);
        }
    }

    public void warn(String message) {
        if (currentLogLevel.ordinal() <= LogLevel.WARN.ordinal()) {
            log("WARN: " + message);
        }
    }

    public void error(String message) {
        if (currentLogLevel.ordinal() <= LogLevel.ERROR.ordinal()) {
            log("ERROR: " + message);
        }
    }

    public void logDay(int day) {
        info("\nDay " + day + ":");
    }

    public void logEcosystem(EcoSystem ecosystem) {
        info(ecosystem.toString());
    }
    private void log(String message) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
            writer.println(message);
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}