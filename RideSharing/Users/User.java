package RideSharing.Users;

/**
 * Abstract base class for all users (Riders and Drivers)
 */
public abstract class User {
    protected String id;
    protected String name;
    protected String phone;
    
    public User(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }
    
    // Getters
    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public String getPhone() {
        return phone;
    }
    
    // Abstract methods for polymorphism
    public abstract void DisplayInfo();
    public abstract String GetRole();
}
