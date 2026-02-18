package RideSharing.Observers;

/**
 * Observer Pattern - Notifies drivers via App notification
 */
public class DriverNotifier implements IRideObserver {
    private String driverName;
    
    public DriverNotifier(String driverName) {
        this.driverName = driverName;
    }
    
    @Override
    public void Update(String rideId, String status) {
        System.out.println("\n[App Notification to Driver " + driverName + "]");
        System.out.println("   Ride " + rideId + " status: " + status);
        
        switch (status) {
            case "REQUESTED":
                System.out.println("   New ride request received!");
                break;
            case "ACCEPTED":
                System.out.println("   You have accepted the ride.");
                break;
            case "IN_PROGRESS":
                System.out.println("   Ride in progress. Please proceed to destination.");
                break;
            case "COMPLETED":
                System.out.println("   Ride completed. Payment will be processed shortly.");
                break;
            case "CANCELLED":
                System.out.println("   Ride has been cancelled.");
                break;
            default:
                System.out.println("   Status update: " + status);
        }
    }
}
