package by.anastasia.multithreading.entity;

import by.anastasia.multithreading.exception.PortException;
import by.anastasia.multithreading.util.PierIdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Pier {
    private static final Logger LOGGER = LogManager.getLogger();
    private static int minMilliseconds = 1000;
    private static int maxMilliseconds = 5000;
    private int id;

    public Pier() {
        this.id = PierIdGenerator.generateId();
    }

    public int getId() {
        return id;
    }

    public void process(Ship ship) throws PortException {
        long shipId = ship.getId();
        ship.setStatus(Ship.Status.PROCESSING);
        LOGGER.log(Level.INFO, "Pier " + id + ": processing ship " + shipId);

        Random random = new Random();
        int milliseconds = random.nextInt(maxMilliseconds - minMilliseconds + 1) + minMilliseconds;
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            throw new PortException("Thread error: " + e);
        }

        ship.setStatus(Ship.Status.FINISHED);
        LOGGER.log(Level.INFO, "Pier " + id + ": finished processing ship " + shipId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pier pier = (Pier) o;

        return id == pier.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "Pier: " + id;
    }
}
