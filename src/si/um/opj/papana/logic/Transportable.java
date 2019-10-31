package si.um.opj.papana.logic;

import si.um.opj.papana.logic.Exceptions.CapacityExceededException;
import si.um.opj.papana.logic.Exceptions.FoodItemTypeException;
import si.um.opj.papana.logic.Exceptions.VolumeExceededException;
import si.um.opj.papana.logic.Exceptions.WeightExceededException;
import si.um.opj.papana.logic.Transport.Vehicle;

public interface Transportable {

        void acceptVehicle (Vehicle vehicle) throws CapacityExceededException, VolumeExceededException, WeightExceededException, FoodItemTypeException;
}