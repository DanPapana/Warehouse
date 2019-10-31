package si.um.opj.papana.logic.Facility;

import si.um.opj.papana.logic.Transportable;
import si.um.opj.papana.logic.Location;
import si.um.opj.papana.logic.Transport.Vehicle;

import java.io.Serializable;

/** @author Slavoj Zizek
 * @version 1
 */

public class Store extends BusinessFacility implements Transportable, Serializable {

    public Store(String name, Location location) {
        super(name, location);
    }

    public void acceptVehicle(Vehicle vehicle) {
        vehicle.unloadFoodItems();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}