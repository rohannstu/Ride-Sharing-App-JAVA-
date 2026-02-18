package RideSharing.Observers;

/**
 * Observer Pattern - Interface for ride observers
 */
public interface IRideObserver {
    /**
     * Update observer with ride status change
     * @param rideId - ID of the ride
     * @param status - new status of the ride
     */
    void Update(String rideId, String status);
}
