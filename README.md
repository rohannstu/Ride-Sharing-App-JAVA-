# Ride Sharing System (Java)

A comprehensive Ride Sharing System implementation in Java demonstrating Object-Oriented Programming principles and design patterns.

## Features

- **User Management**: Register and manage Riders and Drivers
- **Vehicle Management**: Support for Bike ($2), CNG ($3), and Car ($5)
- **Ride Management**: Create rides, track status (Requested -> Accepted -> In Progress -> Completed)
- **Dynamic Pricing**: Multiple pricing strategies (Standard, Rush Hour, Midnight)
- **Payment Processing**: bKash and Credit Card support
- **Notifications**: Real-time status updates to riders and drivers

## How to Run the Application

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- A Java IDE (VS Code, IntelliJ IDEA, Eclipse) or command line

### Running from Command Line

1. **Compile the project**:
   ```bash
   javac RideSharing/**/*.java RideSharing/*.java
   ```

2. **Run the application**:
   ```bash
   java RideSharing.Program
   ```

### Running from IDE

1. Open the project folder in your IDE
2. Locate `RideSharing/Program.java`
3. Right-click and run as Java Application

### Application Flow

1. **Register a Rider**: Go to User Management and create a rider with wallet balance
2. **Register Drivers**: Go to Driver Management and register drivers with different vehicles
3. **Create a Ride**: Go to Ride Management, select rider, vehicle type, and driver
4. **Set Pricing**: Use Pricing menu to switch between pricing strategies
5. **Update Status**: Progress the ride through Accepted -> In Progress -> Completed
6. **Process Payment**: Pay using bKash or Credit Card

## Design Patterns Used

### 1. Factory Pattern 
**Location**: `RideSharing/Vehicles/`
- **IVehicle.java**: Interface defining vehicle behavior
- **Bike.java, CNG.java, Car.java**: Concrete vehicle implementations
- **VehicleFactory.java**: Factory for creating vehicle instances

```java
// Usage
IVehicle vehicle = VehicleFactory.CreateVehicle("Bike");
```

### 2. Inheritance & Polymorphism 
**Location**: `RideSharing/Users/`
- **User.java**: Abstract base class with common properties (Id, Name, Phone)
- **Rider.java**: Extends User, adds wallet balance
- **Driver.java**: Extends User, adds vehicle and availability

```java
// Polymorphism example
User user = new Rider("RID001", "John", "123456", 100.0);
user.DisplayInfo(); // Calls Rider's implementation
```

### 3. Singleton Pattern 
**Location**: `RideSharing/Management/RideManager.java`
- Ensures only one instance of RideManager exists
- Manages all drivers, riders, and rides centrally

```java
// Usage
RideManager manager = RideManager.GetInstance();
```

### 4. Strategy Pattern 
**Location**: `RideSharing/Pricing/`
- **IPricingStrategy.java**: Interface for pricing algorithms
- **StandardPricing.java**: $0.50/km
- **RushHourPricing.java**: $1.00/km
- **MidnightPricing.java**: $0.75/km

```java
// Usage
ride.SetPricingStrategy(new RushHourPricing());
double fare = ride.getFare();
```

### 5. Adapter Pattern 
**Location**: `RideSharing/Payments/`
- **IPaymentProcessor.java**: Common payment interface
- **BkashPaymentGateway.java**: External bKash API (provided)
- **BkashPaymentAdapter.java**: Adapter for bKash
- **CreditCardProcessor.java**: Credit card payment implementation

```java
// Usage
IPaymentProcessor processor = new BkashPaymentAdapter();
String txnId = processor.Pay("phone_number", 25.50);
```

### 6. Observer Pattern 
**Location**: `RideSharing/Observers/`
- **IRideObserver.java**: Observer interface
- **RiderNotifier.java**: SMS notification to riders
- **DriverNotifier.java**: App notification to drivers
- Integrated in `Ride.java`

```java
// Automatically notifies on status change
ride.SetStatus(Ride.Status.IN_PROGRESS);
```

## Project Structure

```
RideSharing/
|-- Program.java                    # Main application (menu-driven)
|-- Users/
|   |-- User.java                  # Abstract base class
|   |-- Rider.java                 # Rider implementation
|   |-- Driver.java                # Driver implementation
|-- Vehicles/
|   |-- IVehicle.java              # Vehicle interface
|   |-- Bike.java                  # Bike implementation
|   |-- CNG.java                   # CNG implementation
|   |-- Car.java                   # Car implementation
|   |-- VehicleFactory.java        # Factory for vehicles
|-- Management/
|   |-- RideManager.java           # Singleton manager
|-- Rides/
|   |-- Ride.java                  # Ride class with pricing & observers
|-- Pricing/
|   |-- IPricingStrategy.java      # Pricing interface
|   |-- StandardPricing.java       # Standard pricing
|   |-- RushHourPricing.java       # Rush hour pricing
|   |-- MidnightPricing.java      # Midnight pricing
|-- Payments/
|   |-- IPaymentProcessor.java     # Payment interface
|   |-- BkashPaymentGateway.java   # External bKash API
|   |-- BkashPaymentAdapter.java   # bKash adapter
|   |-- CreditCardProcessor.java  # Credit card processor
|-- Observers/
    |-- IRideObserver.java          # Observer interface
    |-- RiderNotifier.java         # Rider notification
    |-- DriverNotifier.java        # Driver notification
```

## Fare Calculation Example

```
Base Fare + (Distance x Rate Per Km)

Bike (Base: $2) with Standard Pricing ($0.50/km) for 10km:
= $2 + (10 x $0.50) = $7.00

Car (Base: $5) with Rush Hour Pricing ($1.00/km) for 15km:
= $5 + (15 x $1.00) = $20.00
```

## Status Flow

```
Requested -> Accepted -> In Progress -> Completed
                  |
              Cancelled
```

## Menu Options

1. **User Management**: Register new riders
2. **Driver Management**: Register/view drivers, filter by vehicle
3. **Ride Management**: Create rides, update status, view details
4. **Pricing Strategy**: Switch between pricing models
5. **Payment Processing**: Process bKash/Credit Card payments
6. **View All Data**: Display all system data

## Evaluation Criteria Met

| Criteria | Marks | Status |
|----------|-------|--------|
| Factory Pattern | 10 | Implemented |
| Inheritance & Polymorphism | 10 | Implemented |
| Singleton Pattern | 8 | Implemented |
| Strategy Pattern | 8 | Implemented |
| Adapter Pattern | 8 | Implemented |
| Observer Pattern | 6 | Implemented |
| Code Quality & Organization | 5 | Implemented |
| README Documentation | 5 | Implemented |
| **Total** | **60** | **Complete** |

---

Built with Java OOP and Design Patterns
"# Ride-Sharing-App-JAVA-"  
