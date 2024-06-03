package by.anastasia.multithreading.util;

public class ShipIdGenerator {
    private static final int FIRST_ID = 100_000;
    private static final int LAST_ID = 999_999;
    private static int currentId;

    public ShipIdGenerator() {
        currentId = FIRST_ID;
    }

    public static int generateId() {
        if (currentId == LAST_ID) {
            currentId = FIRST_ID;
        }
        return currentId++;
    }
}
