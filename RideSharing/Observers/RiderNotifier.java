package RideSharing.Observers;

/**
 * Observer Pattern - Notifies riders via SMS
 */
public class RiderNotifier implements IRideObserver {
    private String riderPhone;
    
    public RiderNotifier(String riderPhone) {
        this.riderPhone = riderPhone;
    }
    
    @Override
    public void Update(String rideId, String status) {
        System.out.println("\n[SMS to Rider " + riderPhone + "]");
        System.out.println("   Ride " + rideId + " status: " + status);
        
        switch (status) {
            case "ACCEPTED":
                System.out.println("   Your ride has been accepted by the driver.");
                break;
            case "IN_PROGRESS":
                System.out.println("   Your ride is now in progress. Enjoy your trip!");
                break;
            case "COMPLETED":
                System.out.println("   Your ride has been completed. Thank you for riding!");
                break;
            case "CANCELLED":
                System.out.println("   Your ride has been cancelled.");
                break;
            default:
                System.out.println("   Status update: " + status);
        }
    }
}
