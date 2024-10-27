package main;

import model.Cab;
import service.CabService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CabService cabService = new CabService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Register Cab");
            System.out.println("2. Show Available Cabs");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Cab ID: ");
                    String cabId = scanner.next();
                    System.out.print("Enter Driver Name: ");
                    String driverName = scanner.next();
                    Cab cab = new Cab(cabId, driverName, "Available");
                    cabService.registerCab(cab);
                    System.out.println("Cab registered successfully!");
                    break;

                case 2:
                    System.out.println("Available Cabs:");
                    for (Cab availableCab : cabService.getAvailableCabs()) {
                        System.out.println("Cab ID: " + availableCab.getCabId() +
                                ", Driver: " + availableCab.getDriverName());
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
