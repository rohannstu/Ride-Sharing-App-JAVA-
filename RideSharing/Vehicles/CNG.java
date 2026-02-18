package RideSharing.Vehicles;

/**
 * CNG vehicle implementation with base fare of $3
 */
public class CNG implements IVehicle {
    
    @Override
    public String GetVehicleType() {
        return "CNG";
    }
    
    @Override
    public double GetBaseFare() {
        return 3.0;
    }
}
