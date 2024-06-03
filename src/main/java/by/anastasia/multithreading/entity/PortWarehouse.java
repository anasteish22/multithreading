package by.anastasia.multithreading.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class PortWarehouse {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final PortWarehouse instance = new PortWarehouse();
    private int maxCapacity;

    public static PortWarehouse getInstance() {
        return instance;
    }
}
