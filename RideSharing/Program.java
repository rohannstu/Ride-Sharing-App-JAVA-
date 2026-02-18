package RideSharing;

import RideSharing.Management.RideManager;
import RideSharing.Vehicles.VehicleFactory;
import RideSharing.Vehicles.IVehicle;
import RideSharing.Rides.Ride;
import RideSharing.Pricing.IPricingStrategy;
import RideSharing.Pricing.StandardPricing;
import RideSharing.Pricing.RushHourPricing;
import RideSharing.Pricing.MidnightPricing;
import RideSharing.Payments.IPaymentProcessor;
import RideSharing.Payments.BkashPaymentAdapter;
import RideSharing.Payments.CreditCardProcessor;

import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

/**
 * Main Program - Menu-driven console application for Ride Sharing System
 */
public class Program {
    private static RideManager rideManager = RideManager.GetInstance();
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Ride> activeRides = new HashMap<>();
    
    public static void main(String[] args) {
        System.out.println("+========================================================+");
        System.out.println("|         RIDE SHARING SYSTEM v1.0                      |");
        System.out.println("|                  (Like Uber/Pathao)                  |");
        System.out.println("+========================================================+");
        
        boolean exit = false;
        while (!exit) {
            displayMainMenu();
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    userManagementMenu();
                    break;
                case 2:
                    driverManagementMenu();
                    break;
                case 3:
                    rideManagementMenu();
                    break;
                case 4:
                    pricingMenu();
                    break;
                case 5:
                    paymentMenu();
                    break;
                case 6:
                    viewAllData();
                    break;
                case 0:
                    exit = true;
                    System.out.println("\nThank you for using Ride Sharing System!");
                    System.out.println("   Goodbye!");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private static void displayMainMenu() {
        System.out.println("\n+========================================================+");
        System.out.println("|                    MAIN MENU                          |");
        System.out.println("+========================================================+");
        System.out.println("|  1. User Management (Register Rider)                 |");
        System.out.println("|  2. Driver Management                                |");
        System.out.println("|  3. Ride Management                                  |");
        System.out.println("|  4. Pricing Strategy                                 |");
        System.out.println("|  5. Payment Processing                               |");
        System.out.println("|  6. View All Data                                    |");
        System.out.println("|  0. Exit                                             |");
        System.out.println("+========================================================+");
        System.out.print("Enter your choice: ");
    }
    
    // ==================== USER MANAGEMENT ====================
    private static void userManagementMenu() {
        System.out.println("\n+========================================================+");
        System.out.println("|              USER MANAGEMENT                          |");
        System.out.println("+========================================================+");
        
        System.out.print("Enter Rider Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        
        System.out.print("Enter Initial Wallet Balance: $");
        double balance = getDoubleInput();
        
        String riderId = rideManager.RegisterRider(name, phone, balance);
        System.out.println("\nRider registered successfully!");
        System.out.println("   Rider ID: " + riderId);
    }
    
    // ==================== DRIVER MANAGEMENT ====================
    private static void driverManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n+========================================================+");
            System.out.println("|              DRIVER MANAGEMENT                        |");
            System.out.println("+========================================================+");
            System.out.println("|  1. Register New Driver                              |");
            System.out.println("|  2. View All Drivers                                 |");
            System.out.println("|  3. View Available Drivers by Vehicle Type          |");
            System.out.println("|  0. Back to Main Menu                                |");
            System.out.println("+========================================================+");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    registerDriver();
                    break;
                case 2:
                    rideManager.DisplayAllDrivers();
                    break;
                case 3:
                    viewAvailableDriversByType();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("\nInvalid choice.");
            }
        }
    }
    
    private static void registerDriver() {
        System.out.println("\n--- Register New Driver ---");
        
        System.out.print("Enter Driver Name: ");
        String name = scanner.nextLine();
        
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine();
        
        System.out.println("\nSelect Vehicle Type:");
        System.out.println("1. Bike ($2 base fare)");
        System.out.println("2. CNG ($3 base fare)");
        System.out.println("3. Car ($5 base fare)");
        System.out.print("Enter choice (1-3): ");
        
        int vehicleChoice = getIntInput();
        String vehicleType;
        
        switch (vehicleChoice) {
            case 1:
                vehicleType = "Bike";
                break;
            case 2:
                vehicleType = "CNG";
                break;
            case 3:
                vehicleType = "Car";
                break;
            default:
                System.out.println("Invalid choice. Defaulting to Bike.");
                vehicleType = "Bike";
        }
        
        IVehicle vehicle = VehicleFactory.CreateVehicle(vehicleType);
        String driverId = rideManager.RegisterDriver(name, phone, vehicle);
        
        System.out.println("\nDriver registered successfully!");
        System.out.println("   Driver ID: " + driverId);
        System.out.println("   Vehicle: " + vehicle.GetVehicleType());
    }
    
    private static void viewAvailableDriversByType() {
        System.out.println("\n--- Available Drivers by Vehicle Type ---");
        
        System.out.println("Select Vehicle Type:");
        System.out.println("1. Bike");
        System.out.println("2. CNG");
        System.out.println("3. Car");
        System.out.print("Enter choice (1-3): ");
        
        int choice = getIntInput();
        String vehicleType;
        
        switch (choice) {
            case 1:
                vehicleType = "Bike";
                break;
            case 2:
                vehicleType = "CNG";
                break;
            case 3:
                vehicleType = "Car";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        
        rideManager.DisplayAvailableDriversByType(vehicleType);
    }
    
    // ==================== RIDE MANAGEMENT ====================
    private static void rideManagementMenu() {
        boolean back = false;
        while (!back) {
            System.out.println("\n+========================================================+");
            System.out.println("|              RIDE MANAGEMENT                          |");
            System.out.println("+========================================================+");
            System.out.println("|  1. Create New Ride                                 |");
            System.out.println("|  2. Update Ride Status                              |");
            System.out.println("|  3. View Ride Details                               |");
            System.out.println("|  0. Back to Main Menu                               |");
            System.out.println("+========================================================+");
            System.out.print("Enter your choice: ");
            
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    createRide();
                    break;
                case 2:
                    updateRideStatus();
                    break;
                case 3:
                    viewRideDetails();
                    break;
                case 0:
                    back = true;
                    break;
                default:
                    System.out.println("\nInvalid choice.");
            }
        }
    }
    
    private static void createRide() {
        System.out.println("\n--- Create New Ride ---");
        
        // Show available riders
        var riders = rideManager.GetAllRiders();
        if (riders.isEmpty()) {
            System.out.println("No riders registered. Please register a rider first.");
            return;
        }
        
        System.out.println("\nAvailable Riders:");
        for (var rider : riders) {
            System.out.println("  " + rider.getId() + " - " + rider.getName() + " (Balance: $" + 
                String.format("%.2f", rider.getWalletBalance()) + ")");
        }
        
        System.out.print("\nEnter Rider ID: ");
        String riderId = scanner.nextLine().trim();
        
        // Show available drivers by vehicle type
        System.out.println("\nSelect Vehicle Type:");
        System.out.println("1. Bike");
        System.out.println("2. CNG");
        System.out.println("3. Car");
        System.out.print("Enter choice (1-3): ");
        
        int vehicleChoice = getIntInput();
        String vehicleType;
        
        switch (vehicleChoice) {
            case 1:
                vehicleType = "Bike";
                break;
            case 2:
                vehicleType = "CNG";
                break;
            case 3:
                vehicleType = "Car";
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        
        // Show available drivers for selected vehicle type
        var availableDrivers = rideManager.GetAvailableDrivers(vehicleType);
        if (availableDrivers.isEmpty()) {
            System.out.println("No available drivers for " + vehicleType + ". Please try later.");
            return;
        }
        
        System.out.println("\nAvailable Drivers for " + vehicleType + ":");
        for (var driver : availableDrivers) {
            System.out.println("  " + driver.getId() + " - " + driver.getName() + 
                " (Vehicle: " + driver.getVehicle().GetVehicleType() + ")");
        }
        
        System.out.print("\nEnter Driver ID: ");
        String driverId = scanner.nextLine().trim();
        
        System.out.print("Enter Distance (km): ");
        double distance = getDoubleInput();
        
        try {
            String rideId = rideManager.CreateRide(riderId, driverId, distance);
            Ride ride = rideManager.GetRide(rideId);
            activeRides.put(rideId, ride);
            
            System.out.println("\nRide created successfully!");
            System.out.println("   Ride ID: " + rideId);
            ride.DisplayInfo();
        } catch (Exception e) {
            System.out.println("\nError creating ride: " + e.getMessage());
        }
    }
    
    private static void updateRideStatus() {
        System.out.println("\n--- Update Ride Status ---");
        
        if (activeRides.isEmpty()) {
            System.out.println("No active rides.");
            return;
        }
        
        System.out.println("\nActive Rides:");
        for (String rideId : activeRides.keySet()) {
            Ride ride = activeRides.get(rideId);
            System.out.println("  " + rideId + " - Status: " + ride.getStatus().getDisplayName());
        }
        
        System.out.print("\nEnter Ride ID: ");
        String rideId = scanner.nextLine().trim();
        
        Ride ride = activeRides.get(rideId);
        if (ride == null) {
            System.out.println("Ride not found.");
            return;
        }
        
        System.out.println("\nCurrent Status: " + ride.getStatus().getDisplayName());
        System.out.println("Select New Status:");
        System.out.println("1. Accept (Requested -> Accepted)");
        System.out.println("2. Start Ride (Accepted -> In Progress)");
        System.out.println("3. Complete Ride (In Progress -> Completed)");
        System.out.print("Enter choice (1-3): ");
        
        int choice = getIntInput();
        
        switch (choice) {
            case 1:
                ride.SetStatus(Ride.Status.ACCEPTED);
                System.out.println("\nRide accepted!");
                break;
            case 2:
                ride.SetStatus(Ride.Status.IN_PROGRESS);
                System.out.println("\nRide started!");
                break;
            case 3:
                ride.SetStatus(Ride.Status.COMPLETED);
                System.out.println("\nRide completed!");
                System.out.println("   Fare: $" + String.format("%.2f", ride.getFare()));
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }
    
    private static void viewRideDetails() {
        System.out.println("\n--- View Ride Details ---");
        
        if (activeRides.isEmpty()) {
            System.out.println("No active rides.");
            return;
        }
        
        System.out.print("Enter Ride ID: ");
        String rideId = scanner.nextLine().trim();
        
        Ride ride = activeRides.get(rideId);
        if (ride == null) {
            System.out.println("Ride not found.");
        } else {
            ride.DisplayInfo();
        }
    }
    
    // ==================== PRICING ====================
    private static void pricingMenu() {
        System.out.println("\n--- Set Pricing Strategy ---");
        
        if (activeRides.isEmpty()) {
            System.out.println("No active rides. Create a ride first.");
            return;
        }
        
        System.out.println("\nActive Rides:");
        for (String rideId : activeRides.keySet()) {
            Ride ride = activeRides.get(rideId);
            System.out.println("  " + rideId + " - Current: " + ride.GetPricingStrategyName());
        }
        
        System.out.print("\nEnter Ride ID: ");
        String rideId = scanner.nextLine().trim();
        
        Ride ride = activeRides.get(rideId);
        if (ride == null) {
            System.out.println("Ride not found.");
            return;
        }
        
        System.out.println("\nSelect Pricing Strategy:");
        System.out.println("1. Standard ($0.50/km)");
        System.out.println("2. Rush Hour ($1.00/km)");
        System.out.println("3. Midnight ($0.75/km)");
        System.out.print("Enter choice (1-3): ");
        
        int choice = getIntInput();
        IPricingStrategy strategy;
        
        switch (choice) {
            case 1:
                strategy = new StandardPricing();
                break;
            case 2:
                strategy = new RushHourPricing();
                break;
            case 3:
                strategy = new MidnightPricing();
                break;
            default:
                System.out.println("Invalid choice.");
                return;
        }
        
        ride.SetPricingStrategy(strategy);
        System.out.println("\nPricing strategy updated!");
        System.out.println("   New Strategy: " + ride.GetPricingStrategyName());
        System.out.println("   New Fare: $" + String.format("%.2f", ride.getFare()));
    }
    
    // ==================== PAYMENT ====================
    private static void paymentMenu() {
        System.out.println("\n--- Process Payment ---");
        
        if (activeRides.isEmpty()) {
            System.out.println("No active rides.");
            return;
        }
        
        // Find completed rides
        System.out.println("\nCompleted Rides (ready for payment):");
        boolean hasCompletedRides = false;
        for (String rideId : activeRides.keySet()) {
            Ride ride = activeRides.get(rideId);
            if (ride.getStatus() == Ride.Status.COMPLETED) {
                System.out.println("  " + rideId + " - Fare: $" + String.format("%.2f", ride.getFare()));
                hasCompletedRides = true;
            }
        }
        
        if (!hasCompletedRides) {
            System.out.println("No completed rides pending payment.");
            return;
        }
        
        System.out.print("\nEnter Ride ID: ");
        String rideId = scanner.nextLine().trim();
        
        Ride ride = activeRides.get(rideId);
        if (ride == null || ride.getStatus() != Ride.Status.COMPLETED) {
            System.out.println("Ride not found or not completed.");
            return;
        }
        
        System.out.println("\nSelect Payment Method:");
        System.out.println("1. bKash");
        System.out.println("2. Credit Card");
        System.out.print("Enter choice (1-2): ");
        
        int choice = getIntInput();
        IPaymentProcessor processor;
        
        switch (choice) {
            case 1:
                processor = new BkashPaymentAdapter();
                System.out.print("Enter bKash Phone Number: ");
                String phone = scanner.nextLine().trim();
                
                String txnId = processor.Pay(phone, ride.getFare());
                System.out.println("\nPayment successful!");
                System.out.println("   Transaction ID: " + txnId);
                System.out.println("   Amount: $" + String.format("%.2f", ride.getFare()));
                break;
                
            case 2:
                processor = new CreditCardProcessor();
                System.out.print("Enter Credit Card Number: ");
                String cardNumber = scanner.nextLine().trim();
                
                txnId = processor.Pay(cardNumber, ride.getFare());
                System.out.println("\nPayment successful!");
                System.out.println("   Transaction ID: " + txnId);
                System.out.println("   Amount: $" + String.format("%.2f", ride.getFare()));
                break;
                
            default:
                System.out.println("Invalid choice.");
        }
    }
    
    // ==================== VIEW ALL DATA ====================
    private static void viewAllData() {
        System.out.println("\n+========================================================+");
        System.out.println("|                 ALL DATA VIEW                         |");
        System.out.println("+========================================================+");
        
        System.out.println("\n--- Registered Riders ---");
        var riders = rideManager.GetAllRiders();
        if (riders.isEmpty()) {
            System.out.println("No riders registered.");
        } else {
            for (var rider : riders) {
                rider.DisplayInfo();
                System.out.println();
            }
        }
        
        System.out.println("\n--- Registered Drivers ---");
        var drivers = rideManager.GetAllDrivers();
        if (drivers.isEmpty()) {
            System.out.println("No drivers registered.");
        } else {
            for (var driver : drivers) {
                driver.DisplayInfo();
                System.out.println();
            }
        }
        
        System.out.println("\n--- Active Rides ---");
        if (activeRides.isEmpty()) {
            System.out.println("No active rides.");
        } else {
            for (Ride ride : activeRides.values()) {
                ride.DisplayInfo();
                System.out.println();
            }
        }
    }
    
    // ==================== UTILITY METHODS ====================
    private static int getIntInput() {
        try {
            String input = scanner.nextLine().trim();
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static double getDoubleInput() {
        try {
            String input = scanner.nextLine().trim();
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
