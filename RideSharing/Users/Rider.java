package RideSharing.Users;

/**
 * Rider class - represents a customer who requests rides
 */
public class Rider extends User {
    private double walletBalance;
    
    public Rider(String id, String name, String phone, double walletBalance) {
        super(id, name, phone);
        this.walletBalance = walletBalance;
    }
    
    // Getter and Setter for wallet balance
    public double getWalletBalance() {
        return walletBalance;
    }
    
    public void setWalletBalance(double walletBalance) {
        this.walletBalance = walletBalance;
    }
    
    public void addFunds(double amount) {
        this.walletBalance += amount;
    }
    
    public boolean deductFunds(double amount) {
        if (walletBalance >= amount) {
            walletBalance -= amount;
            return true;
        }
        return false;
    }
    
    @Override
    public void DisplayInfo() {
        System.out.println("=== Rider Information ===");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Role: " + GetRole());
        System.out.println("Wallet Balance: $" + String.format("%.2f", walletBalance));
    }
    
    @Override
    public String GetRole() {
        return "Rider";
    }
}
