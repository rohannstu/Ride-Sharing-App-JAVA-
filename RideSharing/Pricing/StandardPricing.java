package RideSharing.Pricing;

/**
 * Standard Pricing Strategy - $0.50/km
 */
public class StandardPricing implements IPricingStrategy {
    private static final double RATE_PER_KM = 0.50;
    
    @Override
    public double CalculateFare(double distance, double baseFare) {
        return baseFare + (distance * RATE_PER_KM);
    }
    
    @Override
    public String GetStrategyName() {
        return "Standard Pricing";
    }
}
