package by.anastasia.multithreading.entity;

import by.anastasia.multithreading.exception.PortException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PortWarehouse {
    private static final Logger LOGGER = LogManager.getLogger();
    private static PortWarehouse instance = new PortWarehouse();
    private static final AtomicBoolean isCreated = new AtomicBoolean(false);
    private final List<Pier> pierPool = new ArrayList<>();
    private final List<Pier> finishedPiers = new ArrayList<>();
    private final Lock pierLock = new ReentrantLock();
    private static final Queue<Condition> conditions = new ArrayDeque<>();
    private final AtomicInteger storage = new AtomicInteger(0);
    private static final int DEFAULT_PIERS_COUNT = 1;

    private PortWarehouse() {
        for (int i = 0; i < DEFAULT_PIERS_COUNT; i++) {
            pierPool.add(new Pier());
        }
    }

    public static PortWarehouse getInstance() {
        while (instance == null) {
            if (isCreated.compareAndSet(false, true)) {
                instance = new PortWarehouse();
            }
        }
        return instance;
    }

    public Pier getPier() {
        try {
            pierLock.lock();
            if (pierPool.isEmpty()) {
                Condition condition = pierLock.newCondition();
                conditions.add(condition);
                try {
                    condition.await();
                } catch (InterruptedException e) {
                    LOGGER.log(Level.ERROR, e);
                    throw new RuntimeException(e);
                }
            }
            Pier pier = pierPool.removeLast();
            finishedPiers.add(pier);
            return pier;
        } finally {
            pierLock.unlock();
        }
    }

    public boolean releasePier(Pier pier) {
        try {
            pierLock.lock();
            if (!finishedPiers.isEmpty()) {
                pierPool.add(pier);
                Condition condition = conditions.poll();
                if (condition != null) {
                    condition.signal();
                }
            }
            return finishedPiers.remove(pier);
        } finally {
            pierLock.unlock();
        }
    }

    public void addToStorage() {
        storage.incrementAndGet();
    }

    public void removeFromStorage() {
        storage.decrementAndGet();
    }
}
