import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<User> userList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Register User");
            System.out.println("2. View All Users");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    registerUser(scanner);
                    break;
                case 2:
                    displayUsers();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    private static void registerUser(Scanner scanner) {
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();

        if (name.isEmpty() || email.isEmpty() || !email.contains("@")) {
            System.out.println("Invalid input. Please try again.");
            return;
        }

        userList.add(new User(name, email));
        System.out.println("User Registered Successfully!");
    }

    private static void displayUsers() {
        if (userList.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }

        System.out.println("Registered Users:");
        for (User user : userList) {
            System.out.println("Name: " + user.getName() + ", Email: " + user.getEmail());
        }
    }
}
