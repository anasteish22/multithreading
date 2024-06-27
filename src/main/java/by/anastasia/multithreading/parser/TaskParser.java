package by.anastasia.multithreading.parser;

import by.anastasia.multithreading.entity.Ship;
import by.anastasia.multithreading.entity.ShipTask;

public class TaskParser {
    public Ship parse(String task) {
        ShipTask shipTask = ShipTask.valueOf(task);
        return new Ship(shipTask);
    }
}
