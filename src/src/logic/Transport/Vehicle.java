package src.logic.Transport;

import src.logic.Food.FoodItem;
import src.logic.Route;

/** @author Slavoj Zizek
 * @version 1
 */

public abstract class Vehicle implements java.io.Serializable {

    public abstract int getVehiclesMaxVolume();

    private String registrationNumber;
    private int volume;
    private int MaxWeight;
    private float averageSpeed;
    private FoodItem[] cargo;

    public Vehicle() {}

    private Vehicle(String registrationNumber,  float averageSpeed) {
        this.registrationNumber = registrationNumber;
        this.averageSpeed = averageSpeed;
    }

    public Vehicle(String registrationNumber, float averageSpeed, int volume, int capacity, int MaxWeight) {
        this(registrationNumber, averageSpeed);

        if (volume < 0 || MaxWeight < 0 || averageSpeed < 0) {
            throw new IllegalArgumentException();
        }

        this.volume = volume;
        this.MaxWeight = MaxWeight;
        cargo = new FoodItem[capacity];
    }

    public float getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(float averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public int getCargoAmount() {
        return cargo.length;
    }

    public int getMaxWeight() {
        return MaxWeight;
    }

    public void setMaxWeight(int maxWeight) {
        MaxWeight = maxWeight;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }



    public void loadFoodItem (FoodItem[]foodItems) {

        int i = 0;
        int j = 0;

        while (i < cargo.length && foodItems[j] != null) {

            if (cargo[i] == null) {
                cargo[i] = foodItems[j];
                j++;
            }
            i++;
        }
    }


    public void loadFoodItems(FoodItem foodItem) {

        int i = 0;

        while (i < cargo.length) {
            if (cargo[i] == null) {
                cargo[i] = foodItem;
                break;
            }
            i++;
        }
    }


    public void unloadFoodItems() {
        int i = 0;
        while (cargo[i] != null) {
            cargo[i] = null;
            i++;
        }
    }

    public double getTakenSpace() {

        double cargoVolume = 0;
        int i = 0;
        while (cargo[i] != null) {
            cargoVolume += cargo[i].getVolume();
            i++;
        }

        return Math.round(cargoVolume / getVehiclesMaxVolume() * 100);
    }

    public double calculateTravelTime(Route route) {
        return Math.ceil((route.getDistance()/averageSpeed)/24);
    }

    @Override
    public String toString() {
        return registrationNumber + " " + volume + " " + MaxWeight + " " + averageSpeed;
    }
}