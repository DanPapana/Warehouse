package src.logic;

import src.logic.Exceptions.CapacityExceededException;
import src.logic.Exceptions.FoodItemTypeException;
import src.logic.Exceptions.VolumeExceededException;
import src.logic.Exceptions.WeightExceededException;
import src.logic.Transport.Vehicle;

public interface Transportable {

        void acceptVehicle (Vehicle vehicle) throws CapacityExceededException, VolumeExceededException, WeightExceededException, FoodItemTypeException;
}