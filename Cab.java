package model;

public class Cab {
    private String cabId;
    private String driverName;
    private String status; // Available or Unavailable

    public Cab(String cabId, String driverName, String status) {
        this.cabId = cabId;
        this.driverName = driverName;
        this.status = status;
    }

    // Getters and Setters
}
