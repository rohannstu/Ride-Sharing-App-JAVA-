package RideSharing.Pricing;

/**
 * Rush Hour Pricing Strategy - $1.00/km (peak hours)
 */
public class RushHourPricing implements IPricingStrategy {
    private static final double RATE_PER_KM = 1.00;
    
    @Override
    public double CalculateFare(double distance, double baseFare) {
        return baseFare + (distance * RATE_PER_KM);
    }
    
    @Override
    public String GetStrategyName() {
        return "Rush Hour Pricing";
    }
}
