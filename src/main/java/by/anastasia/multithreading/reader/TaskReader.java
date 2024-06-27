package by.anastasia.multithreading.reader;

import by.anastasia.multithreading.exception.ReaderException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskReader {
    private static final Logger LOGGER = LogManager.getLogger();
    private List<String> tasks = new ArrayList<>();

    public List<String> read(String filename) throws ReaderException {
        File file = new File(filename);

        if (filename == null || (!file.exists()) || file.length() == 0) {
            LOGGER.log(Level.ERROR, "File " + filename + " is empty or null");
            throw new ReaderException("File " + filename + " is empty or null");
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String task;

            while ((task = reader.readLine()) != null) {
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }
}