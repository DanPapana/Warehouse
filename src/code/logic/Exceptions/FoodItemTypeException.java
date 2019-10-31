package code.logic.Exceptions;

import code.logic.Food.FoodItemType;

public class FoodItemTypeException extends Exception {

    private FoodItemType inVehicle, inFood;

    public FoodItemTypeException(FoodItemType inVehicle, FoodItemType inFood) {
        this.inVehicle = inVehicle;
        this.inFood = inFood;

    }

    @Override
    public String toString() {
        return "The type of the food in the van is " + inVehicle + " while the food type " + inFood;
    }
}
