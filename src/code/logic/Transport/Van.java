package code.logic.Transport;
import code.logic.Food.FoodItemType;

import java.io.Serializable;

public class Van extends Vehicle implements Serializable {

        private FoodItemType foodType;

        public FoodItemType getFoodType () {
            return foodType;
        }

        public void setFoodType (FoodItemType foodType) {
            this.foodType = foodType;
        }


    public Van(String registrationNumber, float averageSpeed, int volume, int capacity, int MaxWeight) {
        super(registrationNumber, averageSpeed, volume, capacity, MaxWeight);
    }


    public int getVehiclesMaxVolume() {
        return getVolume();
    }

    @Override
    public String toString() {
        return super.toString() + " " + foodType;
    }
}
