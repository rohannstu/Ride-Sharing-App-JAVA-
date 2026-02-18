package RideSharing.Vehicles;

/**
 * Car vehicle implementation with base fare of $5
 */
public class Car implements IVehicle {
    
    @Override
    public String GetVehicleType() {
        return "Car";
    }
    
    @Override
    public double GetBaseFare() {
        return 5.0;
    }
}
