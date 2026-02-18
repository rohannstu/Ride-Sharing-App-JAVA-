package RideSharing.Vehicles;

/**
 * Bike vehicle implementation with base fare of $2
 */
public class Bike implements IVehicle {
    
    @Override
    public String GetVehicleType() {
        return "Bike";
    }
    
    @Override
    public double GetBaseFare() {
        return 2.0;
    }
}
