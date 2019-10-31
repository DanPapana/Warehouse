package src.logic.Exceptions;

public class VolumeExceededException extends Exception {

    private int vehicleVolume, foodVolume;

    public VolumeExceededException(int vehicleVolume, int foodVolume) {
        this.vehicleVolume = vehicleVolume;
        this.foodVolume = foodVolume;

    }

    @Override
    public String toString() {
        return "The volume of the van is " + vehicleVolume + " while the total volume is " + foodVolume;
    }
}
