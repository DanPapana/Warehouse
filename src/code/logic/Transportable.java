package code.logic;

import code.logic.Exceptions.CapacityExceededException;
import code.logic.Exceptions.FoodItemTypeException;
import code.logic.Exceptions.VolumeExceededException;
import code.logic.Exceptions.WeightExceededException;
import code.logic.Transport.Vehicle;

public interface Transportable {

        void acceptVehicle (Vehicle vehicle) throws CapacityExceededException, VolumeExceededException, WeightExceededException, FoodItemTypeException;
}