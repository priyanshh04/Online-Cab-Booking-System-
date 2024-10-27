package service;

import model.Cab;
import java.util.ArrayList;
import java.util.List;

public class CabService {
    private List<Cab> cabs = new ArrayList<>();

    public void registerCab(Cab cab) {
        cabs.add(cab);
    }

    public List<Cab> getAvailableCabs() {
        List<Cab> availableCabs = new ArrayList<>();
        for (Cab cab : cabs) {
            if ("Available".equalsIgnoreCase(cab.getStatus())) {
                availableCabs.add(cab);
            }
        }
        return availableCabs;
    }
}
