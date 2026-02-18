package RideSharing.Rides;

import RideSharing.Users.Rider;
import RideSharing.Users.Driver;
import RideSharing.Pricing.IPricingStrategy;
import RideSharing.Pricing.StandardPricing;
import RideSharing.Observers.IRideObserver;
import RideSharing.Observers.RiderNotifier;
import RideSharing.Observers.DriverNotifier;

import java.util.ArrayList;
import java.util.List;

/**
 * Ride class - integrates Strategy and Observer patterns
 */
public class Ride {
    // Ride status enum
    public enum Status {
        REQUESTED("Requested"),
        ACCEPTED("Accepted"),
        IN_PROGRESS("In Progress"),
        COMPLETED("Completed"),
        CANCELLED("Cancelled");
        
        private final String displayName;
        
        Status(String displayName) {
            this.displayName = displayName;
        }
        
        public String getDisplayName() {
            return displayName;
        }
    }
    
    private String id;
    private Rider rider;
    private Driver driver;
    private double distance;
    private Status status;
    private IPricingStrategy pricingStrategy;
    private double fare;
    private List<IRideObserver> observers;
    
    public Ride(String id, Rider rider, Driver driver, double distance) {
        this.id = id;
        this.rider = rider;
        this.driver = driver;
        this.distance = distance;
        this.status = Status.REQUESTED;
        this.pricingStrategy = new StandardPricing(); // Default strategy
        this.observers = new ArrayList<>();
        
        // Add observers
        AddObserver(new RiderNotifier(rider.getPhone()));
        AddObserver(new DriverNotifier(driver.getName()));
        
        // Calculate initial fare
        CalculateFare();
        
        // Notify observers of initial status
        NotifyObservers();
    }
    
    // Getters
    public String getId() {
        return id;
    }
    
    public Rider getRider() {
        return rider;
    }
    
    public Driver getDriver() {
        return driver;
    }
    
    public double getDistance() {
        return distance;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public double getFare() {
        return fare;
    }
    
    // Set pricing strategy (Strategy Pattern)
    public void SetPricingStrategy(IPricingStrategy strategy) {
        this.pricingStrategy = strategy;
        CalculateFare();
    }
    
    // Calculate fare using current pricing strategy (Strategy Pattern)
    public void CalculateFare() {
        double baseFare = driver.getVehicle().GetBaseFare();
        this.fare = pricingStrategy.CalculateFare(distance, baseFare);
    }
    
    // Get current pricing strategy name
    public String GetPricingStrategyName() {
        return pricingStrategy.GetStrategyName();
    }
    
    // Set ride status and notify observers (Observer Pattern)
    public void SetStatus(Status newStatus) {
        // Validate status flow
        if (!isValidStatusTransition(this.status, newStatus)) {
            System.out.println("Invalid status transition from " + 
                this.status.getDisplayName() + " to " + newStatus.getDisplayName());
            return;
        }
        
        this.status = newStatus;
        NotifyObservers();
        
        // If completed, mark driver as available again
        if (newStatus == Status.COMPLETED || newStatus == Status.CANCELLED) {
            driver.setAvailable(true);
        }
    }
    
    // Check if status transition is valid
    private boolean isValidStatusTransition(Status current, Status next) {
        switch (current) {
            case REQUESTED:
                return next == Status.ACCEPTED || next == Status.CANCELLED;
            case ACCEPTED:
                return next == Status.IN_PROGRESS || next == Status.CANCELLED;
            case IN_PROGRESS:
                return next == Status.COMPLETED;
            case COMPLETED:
            case CANCELLED:
                return false;
            default:
                return false;
        }
    }
    
    // Add observer (Observer Pattern)
    public void AddObserver(IRideObserver observer) {
        observers.add(observer);
    }
    
    // Remove observer
    public void RemoveObserver(IRideObserver observer) {
        observers.remove(observer);
    }
    
    // Notify all observers (Observer Pattern)
    private void NotifyObservers() {
        for (IRideObserver observer : observers) {
            observer.Update(id, status.name());
        }
    }
    
    // Display ride information
    public void DisplayInfo() {
        System.out.println("\n=== Ride Information ===");
        System.out.println("Ride ID: " + id);
        System.out.println("Rider: " + rider.getName() + " (" + rider.getId() + ")");
        System.out.println("Driver: " + driver.getName() + " (" + driver.getId() + ")");
        System.out.println("Vehicle: " + driver.getVehicle().GetVehicleType());
        System.out.println("Distance: " + distance + " km");
        System.out.println("Status: " + status.getDisplayName());
        System.out.println("Pricing Strategy: " + GetPricingStrategyName());
        System.out.println("Fare: $" + String.format("%.2f", fare));
    }
}
