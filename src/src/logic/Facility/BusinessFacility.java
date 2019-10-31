package src.logic.Facility;

import src.logic.Location;

import java.io.Serializable;

public class BusinessFacility implements Serializable {

    private String name;
    private Location location;

   private BusinessFacility() {}

   BusinessFacility(String name, Location location) {
       this.name = name;
       this.location = location;
   }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
       return name + " " + location;
    }
}
