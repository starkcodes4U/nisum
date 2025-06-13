package com.nisum;
import java.io.*;
import java.nio.file.*;
import java.time.*;
import java.time.format.*;

public class GlobalExceptionHandler {
    private static final DateTimeFormatter TIMESTAMP_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    static {
        setupGlobalExceptionHandler();
    }

    public static void main(String[] args) {
        // Test cases
        throw new IllegalStateException("Main thread: Invalid state");
    }

    private static String getLogFilePath() {
        return System.getProperty("user.dir") + File.separator + "error_log.txt";
    }

    private static void setupGlobalExceptionHandler() {
        Thread.setDefaultUncaughtExceptionHandler((thread, ex) -> {
            try {
                logException(thread.getName(), ex);
            } catch (Exception loggingEx) {
                System.err.println("CRITICAL: Failed to log exception:");
                loggingEx.printStackTrace();
            }
        });
    }

    private static synchronized void logException(String threadName, Throwable ex)
            throws IOException {
        String logPath = getLogFilePath();
        rotateLogIfNeeded(new File(logPath));

        try (PrintWriter writer = new PrintWriter(new FileWriter(logPath, true))) {
            writer.printf("%n=== [%s] [%s] ERROR ===%n",
                    LocalDateTime.now().format(TIMESTAMP_FORMAT),
                    threadName);
            ex.printStackTrace(writer);

            System.err.printf("[%s] Error in thread '%s': %s%n",
                    LocalDateTime.now().format(TIMESTAMP_FORMAT),
                    threadName,
                    ex.getMessage());
        }
    }

    private static void rotateLogIfNeeded(File logFile) throws IOException {
        if (logFile.exists() && logFile.length() > 5_000_000) {
            Files.move(logFile.toPath(),
                    Paths.get(logFile.getParent(), "error_log_old.txt"),
                    StandardCopyOption.REPLACE_EXISTING);
        }
    }
}