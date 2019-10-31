package src.logic.Exceptions;


public class WeightExceededException extends Exception {

    private int vehicleWeight, foodWeight;

    public WeightExceededException(int vehicleWeight, int foodWeight) {
        this.vehicleWeight = vehicleWeight;
        this.foodWeight = foodWeight;

    }

    @Override
    public String toString() {
        return "The maximum weight of the van is " + vehicleWeight + " while the total food weight is " + foodWeight;
    }
}
