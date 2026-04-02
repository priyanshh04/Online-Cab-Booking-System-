# Online Cab Booking System (Uber/Ola/Rapido Style - Java)

This repository now contains a **working, runnable Java cab-booking application** with ride lifecycle support similar to real ride-hailing apps.

## What is implemented

### 1) Console app (`Main.java`)
A complete in-memory booking platform with:
- User onboarding with wallet support.
- Driver onboarding and availability tracking.
- Ride booking with auto driver assignment.
- Fare calculation with base fee + per-km pricing + surge multiplier.
- Ride completion and wallet deduction.
- Ride history.

### 2) Swing GUI demo (`UserRegistrationGUI.java`)
A simple desktop UI to:
- Register user.
- Add wallet money.
- Enter route details.
- Book a ride and view fare/log output.

## Run instructions

## Requirements
- Java 17+ (records are used).

### Run console app
```bash
javac Main.java
java Main
```

### Run GUI app
```bash
javac UserRegistrationGUI.java
java UserRegistrationGUI
```

## Menu flow (console)
1. Register user
2. View users
3. Register driver
4. View available drivers
5. Book ride
6. Complete ride
7. View ride history
8. Add user wallet balance
9. Exit

## Notes
- Data is stored in memory (no database).
- The project is intentionally lightweight so it can be run quickly for demos/interviews/assignments.
