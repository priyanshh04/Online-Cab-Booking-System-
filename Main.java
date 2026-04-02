import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        CabBookingApp app = new CabBookingApp();
        app.seedSampleData();
        app.run();
    }
}

class CabBookingApp {
    private final Scanner scanner = new Scanner(System.in);
    private final UserService userService = new UserService();
    private final DriverService driverService = new DriverService();
    private final RideService rideService = new RideService(driverService);

    public void seedSampleData() {
        userService.registerUser("Ava", "ava@example.com", 800);
        userService.registerUser("Noah", "noah@example.com", 600);

        driverService.registerDriver("Raj", "Sedan", "AB12CD3456", 20);
        driverService.registerDriver("Mia", "Mini", "XY98ZT6543", 15);
        driverService.registerDriver("Zara", "Bike", "KL01MN1111", 10);
    }

    public void run() {
        System.out.println("=== Online Cab Booking System ===");

        boolean running = true;
        while (running) {
            printMenu();
            int choice = readInt("Choose an option: ");

            switch (choice) {
                case 1 -> registerUser();
                case 2 -> listUsers();
                case 3 -> registerDriver();
                case 4 -> listAvailableDrivers();
                case 5 -> requestRide();
                case 6 -> completeRide();
                case 7 -> listRides();
                case 8 -> addWalletBalance();
                case 9 -> {
                    running = false;
                    System.out.println("Goodbye!");
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n1. Register User");
        System.out.println("2. View Users");
        System.out.println("3. Register Driver");
        System.out.println("4. View Available Drivers");
        System.out.println("5. Book Ride");
        System.out.println("6. Complete Ride");
        System.out.println("7. View Ride History");
        System.out.println("8. Add User Wallet Balance");
        System.out.println("9. Exit");
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                int value = scanner.nextInt();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                double value = scanner.nextDouble();
                scanner.nextLine();
                return value;
            } catch (InputMismatchException ex) {
                System.out.println("Please enter a valid amount.");
                scanner.nextLine();
            }
        }
    }

    private void registerUser() {
        System.out.print("Enter user name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter email: ");
        String email = scanner.nextLine().trim();
        double balance = readDouble("Enter wallet balance: ");

        try {
            User user = userService.registerUser(name, email, balance);
            System.out.println("User created with ID: " + user.id());
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void listUsers() {
        List<User> users = userService.getUsers();
        if (users.isEmpty()) {
            System.out.println("No users found.");
            return;
        }

        System.out.println("Registered users:");
        for (User user : users) {
            System.out.printf("- %s | %s | Wallet: ₹%.2f%n", user.id(), user.name(), user.walletBalance());
        }
    }

    private void registerDriver() {
        System.out.print("Enter driver name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter vehicle type (Mini/Sedan/Bike): ");
        String vehicleType = scanner.nextLine().trim();
        System.out.print("Enter plate number: ");
        String plate = scanner.nextLine().trim();
        double baseFarePerKm = readDouble("Enter base fare per km: ");

        try {
            Driver driver = driverService.registerDriver(name, vehicleType, plate, baseFarePerKm);
            System.out.println("Driver onboarded with ID: " + driver.id());
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void listAvailableDrivers() {
        List<Driver> drivers = driverService.getAvailableDrivers();
        if (drivers.isEmpty()) {
            System.out.println("No drivers currently available.");
            return;
        }

        System.out.println("Available drivers:");
        for (Driver driver : drivers) {
            System.out.printf("- %s | %s (%s) | %s | ₹%.2f/km%n",
                    driver.id(),
                    driver.name(),
                    driver.vehicleType(),
                    driver.plateNumber(),
                    driver.baseFarePerKm());
        }
    }

    private void requestRide() {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine().trim();
        Optional<User> optionalUser = userService.findById(userId);

        if (optionalUser.isEmpty()) {
            System.out.println("User not found.");
            return;
        }

        User user = optionalUser.get();
        System.out.print("Enter pickup location: ");
        String pickup = scanner.nextLine().trim();
        System.out.print("Enter drop location: ");
        String drop = scanner.nextLine().trim();
        double distance = readDouble("Enter distance in km: ");

        try {
            Ride ride = rideService.bookRide(user, pickup, drop, distance);
            System.out.printf(
                    "Ride booked! Ride ID: %s | Driver: %s | Estimated Fare: ₹%.2f%n",
                    ride.id(),
                    ride.driver().name(),
                    ride.fare());
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    private void completeRide() {
        System.out.print("Enter ride ID to complete: ");
        String rideId = scanner.nextLine().trim();

        Optional<Ride> optionalRide = rideService.findRideById(rideId);
        if (optionalRide.isEmpty()) {
            System.out.println("Ride not found.");
            return;
        }

        Ride ride = optionalRide.get();
        try {
            userService.debitWallet(ride.user().id(), ride.fare());
            rideService.completeRide(rideId);
            System.out.printf("Ride completed! Fare ₹%.2f deducted from %s's wallet.%n", ride.fare(), ride.user().name());
        } catch (IllegalStateException ex) {
            System.out.println("Cannot complete ride: " + ex.getMessage());
        }
    }

    private void listRides() {
        List<Ride> rides = rideService.getRides();
        if (rides.isEmpty()) {
            System.out.println("No rides booked yet.");
            return;
        }

        System.out.println("Ride history:");
        for (Ride ride : rides) {
            System.out.printf("- %s | %s -> %s | User: %s | Driver: %s | ₹%.2f | %s%n",
                    ride.id(),
                    ride.pickup(),
                    ride.drop(),
                    ride.user().name(),
                    ride.driver().name(),
                    ride.fare(),
                    ride.status());
        }
    }

    private void addWalletBalance() {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine().trim();
        double amount = readDouble("Enter amount to add: ");

        try {
            userService.creditWallet(userId, amount);
            User user = userService.findById(userId).orElseThrow();
            System.out.printf("Wallet updated. New balance for %s: ₹%.2f%n", user.name(), user.walletBalance());
        } catch (IllegalArgumentException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}

record User(String id, String name, String email, double walletBalance) {
    User withWallet(double newBalance) {
        return new User(id, name, email, newBalance);
    }
}

record Driver(String id, String name, String vehicleType, String plateNumber, double baseFarePerKm, boolean available) {
    Driver withAvailability(boolean newAvailability) {
        return new Driver(id, name, vehicleType, plateNumber, baseFarePerKm, newAvailability);
    }
}

record Ride(String id, User user, Driver driver, String pickup, String drop, double distanceKm, double fare, RideStatus status) {
    Ride withStatus(RideStatus newStatus) {
        return new Ride(id, user, driver, pickup, drop, distanceKm, fare, newStatus);
    }
}

enum RideStatus {
    ONGOING,
    COMPLETED
}

class UserService {
    private final List<User> users = new ArrayList<>();

    public User registerUser(String name, String email, double initialBalance) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
        if (email.isBlank() || !email.contains("@")) {
            throw new IllegalArgumentException("Email format is invalid.");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Wallet balance cannot be negative.");
        }

        User user = new User("U-" + UUID.randomUUID().toString().substring(0, 8), name, email, initialBalance);
        users.add(user);
        return user;
    }

    public List<User> getUsers() {
        return users;
    }

    public Optional<User> findById(String userId) {
        return users.stream().filter(user -> user.id().equalsIgnoreCase(userId)).findFirst();
    }

    public void debitWallet(String userId, double amount) {
        if (amount <= 0) {
            throw new IllegalStateException("Invalid fare amount.");
        }

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.id().equalsIgnoreCase(userId)) {
                if (user.walletBalance() < amount) {
                    throw new IllegalStateException("Insufficient wallet balance. Add money and try again.");
                }
                users.set(i, user.withWallet(user.walletBalance() - amount));
                return;
            }
        }

        throw new IllegalStateException("User does not exist.");
    }

    public void creditWallet(String userId, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount should be greater than zero.");
        }

        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            if (user.id().equalsIgnoreCase(userId)) {
                users.set(i, user.withWallet(user.walletBalance() + amount));
                return;
            }
        }

        throw new IllegalArgumentException("User not found.");
    }
}

class DriverService {
    private final List<Driver> drivers = new ArrayList<>();

    public Driver registerDriver(String name, String vehicleType, String plateNumber, double baseFarePerKm) {
        if (name.isBlank() || vehicleType.isBlank() || plateNumber.isBlank()) {
            throw new IllegalArgumentException("Driver and vehicle fields cannot be blank.");
        }
        if (baseFarePerKm <= 0) {
            throw new IllegalArgumentException("Base fare must be greater than zero.");
        }

        Driver driver = new Driver(
                "D-" + UUID.randomUUID().toString().substring(0, 8),
                name,
                vehicleType,
                plateNumber,
                baseFarePerKm,
                true);

        drivers.add(driver);
        return driver;
    }

    public List<Driver> getAvailableDrivers() {
        return drivers.stream().filter(Driver::available).toList();
    }

    public void updateAvailability(String driverId, boolean availability) {
        for (int i = 0; i < drivers.size(); i++) {
            Driver driver = drivers.get(i);
            if (driver.id().equalsIgnoreCase(driverId)) {
                drivers.set(i, driver.withAvailability(availability));
                return;
            }
        }

        throw new IllegalStateException("Driver not found.");
    }
}

class RideService {
    private final List<Ride> rides = new ArrayList<>();
    private final DriverService driverService;

    RideService(DriverService driverService) {
        this.driverService = driverService;
    }

    public Ride bookRide(User user, String pickup, String drop, double distanceKm) {
        if (pickup.isBlank() || drop.isBlank()) {
            throw new IllegalArgumentException("Pickup and drop locations are required.");
        }
        if (distanceKm <= 0) {
            throw new IllegalArgumentException("Distance must be greater than 0.");
        }

        List<Driver> availableDrivers = driverService.getAvailableDrivers();
        if (availableDrivers.isEmpty()) {
            throw new IllegalArgumentException("No driver is available right now.");
        }

        Driver assignedDriver = availableDrivers.get(0);
        driverService.updateAvailability(assignedDriver.id(), false);

        double surgeMultiplier = distanceKm > 12 ? 1.25 : 1.0;
        double fare = (assignedDriver.baseFarePerKm() * distanceKm + 40) * surgeMultiplier;

        Ride ride = new Ride(
                "R-" + UUID.randomUUID().toString().substring(0, 8),
                user,
                assignedDriver,
                pickup,
                drop,
                distanceKm,
                Math.round(fare * 100.0) / 100.0,
                RideStatus.ONGOING);

        rides.add(ride);
        return ride;
    }

    public Optional<Ride> findRideById(String rideId) {
        return rides.stream().filter(ride -> ride.id().equalsIgnoreCase(rideId)).findFirst();
    }

    public void completeRide(String rideId) {
        for (int i = 0; i < rides.size(); i++) {
            Ride ride = rides.get(i);
            if (ride.id().equalsIgnoreCase(rideId)) {
                if (ride.status() == RideStatus.COMPLETED) {
                    throw new IllegalStateException("Ride already completed.");
                }
                rides.set(i, ride.withStatus(RideStatus.COMPLETED));
                driverService.updateAvailability(ride.driver().id(), true);
                return;
            }
        }

        throw new IllegalStateException("Ride not found.");
    }

    public List<Ride> getRides() {
        return rides;
    }
}
