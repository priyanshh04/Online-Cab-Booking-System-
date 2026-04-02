import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class UserRegistrationGUI extends JFrame {
    private final JTextField nameField = new JTextField();
    private final JTextField emailField = new JTextField();
    private final JTextField pickupField = new JTextField();
    private final JTextField dropField = new JTextField();
    private final JTextField distanceField = new JTextField();
    private final JTextArea outputArea = new JTextArea();

    private final List<GuiDriver> drivers = new ArrayList<>();
    private GuiUser user;

    public UserRegistrationGUI() {
        setTitle("Mini Ride Booking App");
        setSize(560, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drivers.add(new GuiDriver("Raj", "Sedan", 18));
        drivers.add(new GuiDriver("Mia", "Mini", 14));
        drivers.add(new GuiDriver("Zara", "Bike", 10));

        JPanel formPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        formPanel.add(new JLabel("Name"));
        formPanel.add(nameField);

        formPanel.add(new JLabel("Email"));
        formPanel.add(emailField);

        JButton registerButton = new JButton("Register User");
        registerButton.addActionListener(e -> registerUser());
        formPanel.add(registerButton);

        JButton walletButton = new JButton("Add ₹200 Wallet");
        walletButton.addActionListener(e -> addWallet());
        formPanel.add(walletButton);

        formPanel.add(new JLabel("Pickup"));
        formPanel.add(pickupField);

        formPanel.add(new JLabel("Drop"));
        formPanel.add(dropField);

        formPanel.add(new JLabel("Distance (km)"));
        formPanel.add(distanceField);

        JButton bookRideButton = new JButton("Book Ride");
        bookRideButton.addActionListener(e -> bookRide());
        formPanel.add(bookRideButton);

        JButton clearButton = new JButton("Clear Log");
        clearButton.addActionListener(e -> outputArea.setText(""));
        formPanel.add(clearButton);

        outputArea.setEditable(false);
        outputArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));

        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        setVisible(true);
    }

    private void registerUser() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();

        if (name.isEmpty() || email.isEmpty() || !email.contains("@")) {
            showError("Enter a valid name and email.");
            return;
        }

        user = new GuiUser("U-" + UUID.randomUUID().toString().substring(0, 6), name, email, 500);
        append("User registered: " + user.name + " (" + user.id + ") | Wallet ₹" + user.wallet);
    }

    private void addWallet() {
        if (user == null) {
            showError("Register a user first.");
            return;
        }

        user.wallet += 200;
        append("Wallet updated. New balance: ₹" + user.wallet);
    }

    private void bookRide() {
        if (user == null) {
            showError("Please register user before booking.");
            return;
        }

        String pickup = pickupField.getText().trim();
        String drop = dropField.getText().trim();
        if (pickup.isEmpty() || drop.isEmpty()) {
            showError("Pickup and drop are required.");
            return;
        }

        double distance;
        try {
            distance = Double.parseDouble(distanceField.getText().trim());
        } catch (NumberFormatException ex) {
            showError("Distance must be a number.");
            return;
        }

        if (distance <= 0) {
            showError("Distance must be greater than zero.");
            return;
        }

        GuiDriver driver = drivers.stream().filter(d -> d.available).findFirst().orElse(null);
        if (driver == null) {
            showError("No driver available at the moment.");
            return;
        }

        double fare = driver.ratePerKm * distance + 40;
        if (distance > 12) {
            fare *= 1.25;
        }

        fare = Math.round(fare * 100.0) / 100.0;

        if (user.wallet < fare) {
            showError("Insufficient wallet. Add balance and retry.");
            return;
        }

        user.wallet -= fare;
        driver.available = false;

        append("Ride Booked ✅");
        append("User: " + user.name + " | Driver: " + driver.name + " (" + driver.vehicle + ")");
        append("Route: " + pickup + " -> " + drop + " | Distance: " + distance + " km");
        append("Fare: ₹" + fare + " | Wallet left: ₹" + user.wallet);
        append("----");

        driver.available = true;
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Validation", JOptionPane.ERROR_MESSAGE);
    }

    private void append(String message) {
        outputArea.append(message + "\n");
    }

    static class GuiUser {
        final String id;
        final String name;
        final String email;
        double wallet;

        GuiUser(String id, String name, String email, double wallet) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.wallet = wallet;
        }
    }

    static class GuiDriver {
        final String name;
        final String vehicle;
        final double ratePerKm;
        boolean available = true;

        GuiDriver(String name, String vehicle, double ratePerKm) {
            this.name = name;
            this.vehicle = vehicle;
            this.ratePerKm = ratePerKm;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserRegistrationGUI::new);
    }
}
