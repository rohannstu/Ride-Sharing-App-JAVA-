package RideSharing.Management;

import RideSharing.Users.Driver;
import RideSharing.Users.Rider;
import RideSharing.Rides.Ride;
import RideSharing.Vehicles.IVehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Singleton Pattern - Manages drivers, riders, and rides
 */
public class RideManager {
    // Singleton instance
    private static RideManager instance;
    
    // Data storage
    private Map<String, Driver> drivers;
    private Map<String, Rider> riders;
    private Map<String, Ride> rides;
    private int driverIdCounter;
    private int riderIdCounter;
    private int rideIdCounter;
    
    // Private constructor for Singleton
    private RideManager() {
        drivers = new HashMap<>();
        riders = new HashMap<>();
        rides = new HashMap<>();
        driverIdCounter = 1;
        riderIdCounter = 1;
        rideIdCounter = 1;
    }
    
    // Get singleton instance
    public static RideManager GetInstance() {
        if (instance == null) {
            instance = new RideManager();
        }
        return instance;
    }
    
    // Driver Management
    public String RegisterDriver(String name, String phone, IVehicle vehicle) {
        String id = "DRV" + String.format("%03d", driverIdCounter++);
        Driver driver = new Driver(id, name, phone, vehicle);
        drivers.put(id, driver);
        return id;
    }
    
    public List<Driver> GetAllDrivers() {
        return new ArrayList<>(drivers.values());
    }
    
    public List<Driver> GetAvailableDrivers(String vehicleType) {
        List<Driver> availableDrivers = new ArrayList<>();
        for (Driver driver : drivers.values()) {
            if (driver.isAvailable() && 
                driver.getVehicle().GetVehicleType().equalsIgnoreCase(vehicleType)) {
                availableDrivers.add(driver);
            }
        }
        return availableDrivers;
    }
    
    public Driver GetDriver(String driverId) {
        return drivers.get(driverId);
    }
    
    // Rider Management
    public String RegisterRider(String name, String phone, double initialBalance) {
        String id = "RID" + String.format("%03d", riderIdCounter++);
        Rider rider = new Rider(id, name, phone, initialBalance);
        riders.put(id, rider);
        return id;
    }
    
    public List<Rider> GetAllRiders() {
        return new ArrayList<>(riders.values());
    }
    
    public Rider GetRider(String riderId) {
        return riders.get(riderId);
    }
    
    // Ride Management
    public String CreateRide(String riderId, String driverId, double distance) {
        Rider rider = riders.get(riderId);
        Driver driver = drivers.get(driverId);
        
        if (rider == null || driver == null) {
            throw new IllegalArgumentException("Invalid rider or driver ID");
        }
        
        if (!driver.isAvailable()) {
            throw new IllegalStateException("Driver is not available");
        }
        
        String rideId = "RIDE" + String.format("%03d", rideIdCounter++);
        Ride ride = new Ride(rideId, rider, driver, distance);
        rides.put(rideId, ride);
        
        // Mark driver as unavailable
        driver.setAvailable(false);
        
        return rideId;
    }
    
    public Ride GetRide(String rideId) {
        return rides.get(rideId);
    }
    
    public List<Ride> GetAllRides() {
        return new ArrayList<>(rides.values());
    }
    
    // Display methods
    public void DisplayAllDrivers() {
        System.out.println("\n=== All Registered Drivers ===");
        if (drivers.isEmpty()) {
            System.out.println("No drivers registered.");
        } else {
            for (Driver driver : drivers.values()) {
                driver.DisplayInfo();
                System.out.println();
            }
        }
    }
    
    public void DisplayAllRiders() {
        System.out.println("\n=== All Registered Riders ===");
        if (riders.isEmpty()) {
            System.out.println("No riders registered.");
        } else {
            for (Rider rider : riders.values()) {
                rider.DisplayInfo();
                System.out.println();
            }
        }
    }
    
    public void DisplayAvailableDriversByType(String vehicleType) {
        System.out.println("\n=== Available Drivers for " + vehicleType + " ===");
        List<Driver> available = GetAvailableDrivers(vehicleType);
        if (available.isEmpty()) {
            System.out.println("No available drivers for " + vehicleType);
        } else {
            for (Driver driver : available) {
                driver.DisplayInfo();
                System.out.println();
            }
        }
    }
}
