package code.logic;

import code.logic.Facility.Store;
import code.logic.Facility.Warehouse;

/** @author Slavoj Zizek
 * @version 1
 */

public class Route {

    private Store store;
    private Warehouse warehouse;
    private float distance;

    public Route() {}

    public Route(Store store, Warehouse warehouse, float distance) {
        this.store = store;
        this.warehouse = warehouse;
        this.distance = distance;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Warehouse getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse = warehouse;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Override
    public String toString() {
        return store + " " + warehouse + " " + distance;
    }
}
