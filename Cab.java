package model;

public class Cab {
    private String cabId;
    private String driverName;
    private String status; // Available or Unavailable

    // Constructor with validation
    public Cab(String cabId, String driverName, String status) {
        if (cabId == null || cabId.isEmpty()) {
            throw new IllegalArgumentException("Cab ID cannot be null or empty");
        }
        if (driverName == null || driverName.isEmpty()) {
            throw new IllegalArgumentException("Driver name cannot be null or empty");
        }
        if (!status.equals("Available") && !status.equals("Unavailable")) {
            throw new IllegalArgumentException("Status must be 'Available' or 'Unavailable'");
        }
        this.cabId = cabId;
        this.driverName = driverName;
        this.status = status;
    }

    // Getters and Setters
    public String getCabId() {
        return cabId;
    }

    public void setCabId(String cabId) {
        if (cabId == null || cabId.isEmpty()) {
            throw new IllegalArgumentException("Cab ID cannot be null or empty");
        }
        this.cabId = cabId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        if (driverName == null || driverName.isEmpty()) {
            throw new IllegalArgumentException("Driver name cannot be null or empty");
        }
        this.driverName = driverName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        if (!status.equals("Available") && !status.equals("Unavailable")) {
            throw new IllegalArgumentException("Status must be 'Available' or 'Unavailable'");
        }
        this.status = status;
    }

    // Optional: Override toString for better debugging
    @Override
    public String toString() {
        return "Cab{" +
                "cabId='" + cabId + '\'' +
                ", driverName='" + driverName + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
