package code.logic.Exceptions;

public class CapacityExceededException extends Exception {

    private int foodAmount, vehicleStorage;

    public CapacityExceededException(int foodAmount, int vehicleStorage) {
       this.foodAmount = foodAmount;
       this.vehicleStorage = vehicleStorage;
    }

    @Override
    public String toString() {
        return "The capacity of the van is " + vehicleStorage + " while the capacity of the food is " + foodAmount;
    }
}

