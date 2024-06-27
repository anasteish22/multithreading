package by.anastasia.multithreading.util;

import by.anastasia.multithreading.entity.Ship;

public class ShipIdGenerator {
    private static ShipIdGenerator instance;
    private static final int FIRST_ID = 100_000;
    private static final int LAST_ID = 999_999;
    private static int currentId;

    private ShipIdGenerator() {
        currentId = FIRST_ID;
    }

    public static ShipIdGenerator getInstance() {
        if (instance == null) {
            instance = new ShipIdGenerator();
        }
        return instance;
    }

    public int generateId() {
        if (currentId == LAST_ID) {
            currentId = FIRST_ID;
        }
        return currentId++;
    }
}
