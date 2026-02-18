package RideSharing.Users;

import RideSharing.Vehicles.IVehicle;

/**
 * Driver class - represents a driver who provides rides
 */
public class Driver extends User {
    private IVehicle vehicle;
    private boolean isAvailable;
    
    public Driver(String id, String name, String phone, IVehicle vehicle) {
        super(id, name, phone);
        this.vehicle = vehicle;
        this.isAvailable = true;
    }
    
    // Getter and Setter for vehicle
    public IVehicle getVehicle() {
        return vehicle;
    }
    
    public void setVehicle(IVehicle vehicle) {
        this.vehicle = vehicle;
    }
    
    // Getter and Setter for availability
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    
    public void toggleAvailability() {
        isAvailable = !isAvailable;
    }
    
    @Override
    public void DisplayInfo() {
        System.out.println("=== Driver Information ===");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Role: " + GetRole());
        System.out.println("Vehicle Type: " + vehicle.GetVehicleType());
        System.out.println("Base Fare: $" + String.format("%.2f", vehicle.GetBaseFare()));
        System.out.println("Available: " + (isAvailable ? "Yes" : "No"));
    }
    
    @Override
    public String GetRole() {
        return "Driver";
    }
}
