package service;

import model.Cab;
import java.util.ArrayList;
import java.util.List;

public class CabService {
    private final List<Cab> cabs = new ArrayList<>();

    public synchronized void registerCab(Cab cab) {
        if (cab == null) {
            throw new IllegalArgumentException("Cab cannot be null");
        }
        for (Cab existingCab : cabs) {
            if (existingCab.getCabId().equals(cab.getCabId())) {
                throw new IllegalArgumentException("Cab with ID " + cab.getCabId() + " is already registered");
            }
        }
        cabs.add(cab);
    }

    public synchronized List<Cab> getAvailableCabs() {
        List<Cab> availableCabs = new ArrayList<>();
        for (Cab cab : cabs) {
            if ("Available".equalsIgnoreCase(cab.getStatus())) {
                availableCabs.add(cab);
            }
        }
        return availableCabs;
    }

    public synchronized void removeCab(String cabId) {
        cabs.removeIf(cab -> cab.getCabId().equals(cabId));
    }
}
