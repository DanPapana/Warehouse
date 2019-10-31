package code.logic.Facility;

import code.logic.*;
import code.logic.Exceptions.CapacityExceededException;
import code.logic.Exceptions.FoodItemTypeException;
import code.logic.Exceptions.VolumeExceededException;
import code.logic.Exceptions.WeightExceededException;
import code.logic.Food.FoodItem;

import code.logic.Transport.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;


/** @author Slavoj Zizek
 * @version 1
 */

public class Warehouse extends BusinessFacility implements Transportable, Serializable {

    private int capacity;
    private FoodItem[] foodItems;

    /**
     *  Ah yes, a constructor.
     */

    /**
     * @param name The name of the warehouse
     * @param location The location of the warehouse
     * A parametrized constructor that accepts @param name and @param location
     */

    /**
     * <p> A constructor that calls the previous parametrized constructor with the keyword "this" and assigns the name and location
     * It then adds the capacity to it as well. </p>
     * @param capacity The maximum capacity of the si.um.opj.papana.logic.Facility.Warehouse
     * @param name The name of the warehouse
     * @param location The location of the warehouse
     */

    public Warehouse(String name, Location location, int capacity) {
        super(name, location);
        this.capacity = capacity;
        this.foodItems = new FoodItem[capacity];
    }

    /**
     * <p> A getter method for the location that, surprisingly, returns the location </p>
     * @return location The location
     */

    /**
     * Sets the new location of the warehouse
     * @param location The new location of the warehouse
     */

    /**
     * A getter method for name
     * @return name
     */

    /**
     * A setter method for the name
     * @param name The new name of the si.um.opj.papana.logic.Facility.Warehouse
     */

    /**
     * A getter method for the capacity
     * @return capacity
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * A setter method for the capacity
     * @param capacity The new capacity of the si.um.opj.papana.logic.Facility.Warehouse
     */

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * <p> Adds an item to the si.um.opj.papana.logic.Food.FoodItem array of objects </p>
     * @param foodItem The foodItem we are trying to remove
     */

    public void addItem(FoodItem foodItem) {
        if (stillValid(foodItem)) {
            this.foodItems[returnTheNumberOfFoodItems()] = foodItem;
        }
    }

    /**
     * <p> Removes a desired foodItem, and then makes sure that the array is tucked in before wishing it a good night</p>
     * @param foodItem The food item we are trying to remove
     */

    private void removeItem(FoodItem foodItem) {
        for (int i = 0; i < returnTheNumberOfFoodItems(); i++) {
            if (foodItems[i] == foodItem) {
                for (int j = i; j < returnTheNumberOfFoodItems(); j++) {
                    foodItems[j] = foodItems[j+1];
                }
                foodItems[returnTheNumberOfFoodItems() - 1] = null;
                break;
            }
        }
    }

    /**
     * <p> This method finds out how many food items are there in the array by finding the last non-existing item in the list</p>
     * @return i The number of the food items
     */

    public int returnTheNumberOfFoodItems() {
        for (int i = 0; i < capacity; i++) {
            if (foodItems[i] == null) {
                return i;
            }
        }
        return capacity;
    }

    /**
     * <p> Finds out whether a food item with a specific label exists in the si.um.opj.papana.logic.Facility.Warehouse </p>
     * @param foodItem The food item's label we are trying to find
     * @return The boolean value of the search
     */

    public boolean foodItemExists(FoodItem foodItem) {
        for (int i = 0; i < returnTheNumberOfFoodItems(); i++) {
            if (foodItems[i].getLabel().equals(foodItem.getLabel())) {
                return true;
            }
        }
        return false;
    }

    private boolean stillValid(FoodItem foodItem) {
        return foodItem.getExpirationDate().compareTo(LocalDate.now().plusDays(3)) > 0;
    }

    public Warehouse getWarehouse() {
        return this;
    }

    public void acceptVehicle(Vehicle vehicle) throws CapacityExceededException, VolumeExceededException,
            WeightExceededException, FoodItemTypeException {

        int totalVolume = 0;
        int totalWeight = 0;

        for (int i = 0; i < returnTheNumberOfFoodItems(); i++) {
            totalVolume += foodItems[i].getVolume();
            totalWeight += foodItems[i].getWeight();
        }

        if (vehicle.getVehiclesMaxVolume() < totalVolume) {
            if (vehicle instanceof Van) {
                vehicle.unloadFoodItems();
            }
            throw new VolumeExceededException(vehicle.getVehiclesMaxVolume(), totalVolume);
        }

        if (vehicle.getCargoAmount() < returnTheNumberOfFoodItems()) {
            if (vehicle instanceof Van) {
                vehicle.unloadFoodItems();
            }
            throw new CapacityExceededException(returnTheNumberOfFoodItems(), vehicle.getCargoAmount());
        }

        if (vehicle.getMaxWeight() < totalWeight) {
            if (vehicle instanceof Van) {
                vehicle.unloadFoodItems();
            }
            throw new WeightExceededException(vehicle.getMaxWeight(), totalWeight);
        }

        if (vehicle instanceof Van) {

            for (int i = 0; i < returnTheNumberOfFoodItems(); i++) {

                if (((Van) vehicle).getFoodType() != foodItems[i].getFoodType()) {
                    vehicle.unloadFoodItems();
                    throw new FoodItemTypeException(((Van) vehicle).getFoodType(), foodItems[i].getFoodType());
                }

                vehicle.loadFoodItems(foodItems[i]);
                removeItem(foodItems[i]);

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    System.out.println("Thread interrupted.");
                }
            }

        } else if (vehicle instanceof Truck) {
            vehicle.loadFoodItem(foodItems);
            for (int i = 0; i < returnTheNumberOfFoodItems(); i++) {
                removeItem(foodItems[i]);
            }
        }
    }

    @Override
    public String toString() {
        return super.toString() + " " + Arrays.toString(foodItems);
    }
}