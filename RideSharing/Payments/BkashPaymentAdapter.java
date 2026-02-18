package RideSharing.Payments;

/**
 * Adapter Pattern - Adapts BkashPaymentGateway to IPaymentProcessor
 */
public class BkashPaymentAdapter implements IPaymentProcessor {
    private BkashPaymentGateway bkashGateway;
    
    public BkashPaymentAdapter() {
        this.bkashGateway = new BkashPaymentGateway();
    }
    
    @Override
    public String Pay(String paymentInfo, double amount) {
        // paymentInfo should be the phone number for bKash
        String transactionId = bkashGateway.SendMoney(paymentInfo, amount);
        bkashGateway.CheckStatus(transactionId);
        return transactionId;
    }
    
    @Override
    public String GetPaymentMethod() {
        return "bKash";
    }
}
