package RideSharing.Vehicles;

/**
 * Factory Pattern - Creates vehicle instances based on type
 */
public class VehicleFactory {
    
    /**
     * Creates a vehicle based on the specified type
     * @param type - "Bike", "CNG", or "Car"
     * @return IVehicle instance
     */
    public static IVehicle CreateVehicle(String type) {
        switch (type.toLowerCase()) {
            case "bike":
                return new Bike();
            case "cng":
                return new CNG();
            case "car":
                return new Car();
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
}
