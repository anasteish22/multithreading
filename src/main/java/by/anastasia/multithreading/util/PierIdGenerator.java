package by.anastasia.multithreading.util;

public class PierIdGenerator {
    private static PierIdGenerator instance;
    private static final int FIRST_ID = 100;
    private static final int LAST_ID = 999;
    private static int currentId;

    public PierIdGenerator() {
        currentId = FIRST_ID;
    }

    public static PierIdGenerator getInstance() {
        if (instance == null) {
            instance = new PierIdGenerator();
        }
        return instance;
    }

    public int generateId() {
        if (currentId == LAST_ID) {
            currentId = FIRST_ID;
        }
        return ++currentId;
    }
}
