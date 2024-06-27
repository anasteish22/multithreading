package by.anastasia.multithreading.entity;

import by.anastasia.multithreading.util.PierIdGenerator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Pier {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final int MIN_MILLISECONDS = 1000;
    private static final int MAX_MILLISECONDS = 5000;
    private int id;

    public Pier() {
        this.id = PierIdGenerator.getInstance().generateId();
    }

    public int getId() {
        return id;
    }

    public void process(Ship ship) {
        long shipId = ship.getId();
        ship.setCondition(ShipCondition.PROCESSING);
        LOGGER.log(Level.INFO, "Pier " + id + ": processing ship " + shipId);

        Random random = new Random();
        int milliseconds = random.nextInt(MAX_MILLISECONDS - MIN_MILLISECONDS + 1) + MIN_MILLISECONDS;
        try {
            TimeUnit.MILLISECONDS.sleep(milliseconds);
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, e);
            throw new RuntimeException(e);
        }

        ship.setCondition(ShipCondition.FINISHED);
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
