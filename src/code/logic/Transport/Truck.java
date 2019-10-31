package code.logic.Transport;

import java.io.Serializable;

public class Truck extends Vehicle implements Serializable {

    private int numberOfTrailers;

    public Truck(String registrationNumber, float averageSpeed, int volume, int capacity, int MaxWeight, int numberOfTrailers) {
        super(registrationNumber, averageSpeed, volume, capacity, MaxWeight);
        this.numberOfTrailers = numberOfTrailers;
    }

    public int getNumberOfTrailers() {
        return numberOfTrailers;
    }

    public void setNumberOfTrailers(int numberOfTrailers) {
        this.numberOfTrailers = numberOfTrailers;
    }

    @Override
    public int getVehiclesMaxVolume() {
        return getVolume() * numberOfTrailers;
    }

    @Override
    public String toString() {
        return super.toString() + " with " + numberOfTrailers + " trailers.";
    }
}