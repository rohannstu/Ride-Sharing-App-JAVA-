package RideSharing.Payments;

import java.util.Random;

/**
 * Adapter Pattern - Credit Card payment processor
 */
public class CreditCardProcessor implements IPaymentProcessor {
    private Random random;
    
    public CreditCardProcessor() {
        this.random = new Random();
    }
    
    @Override
    public String Pay(String paymentInfo, double amount) {
        // paymentInfo should be the card number
        String lastFour = paymentInfo.length() >= 4 
            ? paymentInfo.substring(paymentInfo.length() - 4) 
            : paymentInfo;
        
        System.out.println("Credit Card: Processing payment of $" + amount);
        System.out.println("Credit Card: Card ending in " + lastFour);
        
        // Generate transaction ID
        String transactionId = "CC" + (1000 + random.nextInt(9000));
        System.out.println("Credit Card: Transaction " + transactionId + " successful");
        
        return transactionId;
    }
    
    @Override
    public String GetPaymentMethod() {
        return "Credit Card";
    }
}
