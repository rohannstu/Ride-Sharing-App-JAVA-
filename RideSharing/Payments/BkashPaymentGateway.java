package RideSharing.Payments;

/**
 * External bKash Payment Gateway (Provided - Cannot modify)
 */
public class BkashPaymentGateway {
    public String SendMoney(String phoneNumber, double amount) {
        System.out.println("bKash: Sending $" + amount + " to " + phoneNumber);
        return "TXN" + new java.util.Random().nextInt(9000) + 1000;
    }
    
    public boolean CheckStatus(String transactionId) {
        System.out.println("bKash: " + transactionId + " successful");
        return true;
    }
}
