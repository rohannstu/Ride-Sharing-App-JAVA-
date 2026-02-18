package RideSharing.Pricing;

/**
 * Strategy Pattern - Interface for pricing strategies
 */
public interface IPricingStrategy {
    /**
     * Calculate fare based on distance and base fare
     * @param distance - distance in kilometers
     * @param baseFare - base fare of the vehicle
     * @return calculated fare
     */
    double CalculateFare(double distance, double baseFare);
    
    /**
     * Get the name of the pricing strategy
     * @return strategy name
     */
    String GetStrategyName();
}
