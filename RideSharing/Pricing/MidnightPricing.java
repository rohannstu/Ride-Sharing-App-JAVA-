package RideSharing.Pricing;

/**
 * Midnight Pricing Strategy - $0.75/km (late night hours)
 */
public class MidnightPricing implements IPricingStrategy {
    private static final double RATE_PER_KM = 0.75;
    
    @Override
    public double CalculateFare(double distance, double baseFare) {
        return baseFare + (distance * RATE_PER_KM);
    }
    
    @Override
    public String GetStrategyName() {
        return "Midnight Pricing";
    }
}
