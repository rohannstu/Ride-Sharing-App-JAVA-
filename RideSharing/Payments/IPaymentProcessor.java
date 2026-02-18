package RideSharing.Payments;

/**
 * Adapter Pattern - Interface for payment processors
 */
public interface IPaymentProcessor {
    /**
     * Process payment with given information
     * @param paymentInfo - payment details (phone number, card number, etc.)
     * @param amount - amount to pay
     * @return transaction ID
     */
    String Pay(String paymentInfo, double amount);
    
    /**
     * Get the payment method name
     * @return payment method
     */
    String GetPaymentMethod();
}
