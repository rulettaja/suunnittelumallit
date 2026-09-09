package singleton;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public final class Logger {

    private static final String DEFAULT_FILE_NAME = "log.txt";
    private static final Logger INSTANCE = new Logger();

    private BufferedWriter writer;

    private Logger() {
        setFileName(DEFAULT_FILE_NAME);
    }

    public static Logger getInstance() {
        return INSTANCE;
    }

    public synchronized void setFileName(String fileName) {
        close();
        try {
            writer = Files.newBufferedWriter(
                    Path.of(fileName),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException | RuntimeException exception) {
            writer = null;
            System.err.println("Unable to open log file: " + exception.getMessage());
        }
    }

    public synchronized void write(String message) {
        if (writer == null) {
            return;
        }
        try {
            writer.write(message);
            writer.newLine();
            writer.flush();
        } catch (IOException exception) {
            System.err.println("Unable to write log message: " + exception.getMessage());
        }
    }

    public synchronized void close() {
        if (writer == null) {
            return;
        }
        try {
            writer.close();
        } catch (IOException exception) {
            System.err.println("Unable to close log file: " + exception.getMessage());
        } finally {
            writer = null;
        }
    }
}