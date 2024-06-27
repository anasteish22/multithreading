package by.anastasia.multithreading.entity;

import by.anastasia.multithreading.util.ShipIdGenerator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Ship extends Thread {
    private static final Logger LOGGER = LogManager.getLogger();
    private long id;
    private ShipTask shipTask;
    private ShipCondition shipCondition;


    public Ship(ShipTask shipTask) {
        this.id = ShipIdGenerator.getInstance().generateId();
        this.shipTask = shipTask;
        this.shipCondition = ShipCondition.FREE;
    }

    @Override
    public long getId() {
        return id;
    }

    public ShipTask getTask() {
        return shipTask;
    }

    public void setTask(ShipTask shipTask) {
        this.shipTask = shipTask;
    }

    public ShipCondition getCondition() {
        return shipCondition;
    }

    public void setCondition(ShipCondition shipCondition) {
        this.shipCondition = shipCondition;
    }

    @Override
    public void run() {
        PortWarehouse port = PortWarehouse.getInstance();
        Pier pier = port.getPier();
        pier.process(this);

        if (shipTask == ShipTask.LOAD) {
            port.removeFromStorage();
        } else if (shipTask == ShipTask.UNLOAD) {
            port.addToStorage();
        } else if (shipTask == ShipTask.LOAD_UNLOAD) {
            port.removeFromStorage();
            port.addToStorage();
        }
        port.releasePier(pier);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ship ship = (Ship) o;

        if (id != ship.id) return false;
        if (shipTask != ship.shipTask) return false;
        return shipCondition == ship.shipCondition;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (shipTask != null ? shipTask.hashCode() : 0);
        result = 31 * result + (shipCondition != null ? shipCondition.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ship{");
        sb.append("id=").append(id);
        sb.append(", shipTask=").append(shipTask);
        sb.append(", shipCondition=").append(shipCondition);
        sb.append('}');
        return sb.toString();
    }
}
