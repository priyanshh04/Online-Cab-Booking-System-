package main;

import model.Cab;
import service.CabService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CabService cabService = new CabService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Register Cab");
            System.out.println("2. Show Available Cabs");
            System.out.println("3. Exit");

            int choice;
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // Clear invalid input
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Cab ID: ");
                    String cabId = scanner.nextLine().trim();
                    System.out.print("Enter Driver Name: ");
                    String driverName = scanner.nextLine().trim();

                    if (cabId.isEmpty() || driverName.isEmpty()) {
                        System.out.println("Cab ID and Driver Name cannot be empty.");
                        break;
                    }

                    try {
                        Cab cab = new Cab(cabId, driverName, "Available");
                        cabService.registerCab(cab);
                        System.out.println("Cab registered successfully!");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }

                case 2 -> {
                    System.out.println("Available Cabs:");
                    if (cabService.getAvailableCabs().isEmpty()) {
                        System.out.println("No cabs are currently available.");
                    } else {
                        for (Cab availableCab : cabService.getAvailableCabs()) {
                            System.out.println("Cab ID: " + availableCab.getCabId() +
                                    ", Driver: " + availableCab.getDriverName());
                        }
                    }
                }

                case 3 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                }

                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
