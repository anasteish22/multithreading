package by.anastasia.multithreading.entity;

import by.anastasia.multithreading.util.ShipIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ship extends Thread {
    private static final Logger LOGGER = LogManager.getLogger();
    private long id;
    private int maxCapacity;
    private Task task;
    private Status status;

    public enum Task {
        LOAD,
        UNLOAD,
        LOAD_UNLOAD
    }

    public enum Status {
        WAITING,
        PROCESSING,
        FINISHED
    }

    public Ship(int maxCapacity, Task task) {
        this.id = ShipIdGenerator.generateId();
        this.maxCapacity = maxCapacity;
        this.task = task;
        this.status = Status.WAITING;
    }

    @Override
    public long getId() {
        return id;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public void run() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (id != ship.id) return false;
        if (maxCapacity != ship.maxCapacity) return false;
        if (task != ship.task) return false;
        return status == ship.status;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + maxCapacity;
        result = 31 * result + (task != null ? task.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Ship: ");
        stringBuilder.append("id = ").append(id).append(", ");
        stringBuilder.append("maxCapacity = ").append(maxCapacity).append(", ");
        stringBuilder.append("task = ").append(task);

        return stringBuilder.toString();
    }
}
