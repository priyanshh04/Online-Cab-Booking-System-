# Online Cab Booking System

## Overview
This project is a comprehensive implementation of an Online Cab Booking System, developed incrementally through multiple reviews. It demonstrates features ranging from core console-based functionalities to GUI-based interfaces and web application capabilities using Maven and Spring Boot.

---

## Features
### Review 1: Console-Based Project
1. **Core Functionality**:
   - Add and view user details.
   - Register and view cab details.
   - Interactive console menu for managing users and cabs.

2. **Validation**:
   - Ensures all inputs (e.g., name, email, cab details) are non-empty and formatted correctly.

---

### Review 2: HTML, CSS, and JavaScript Templates
1. **User Management Templates**:
   - Login, registration, and user profile HTML templates.
   - Styled using CSS and Bootstrap for responsiveness.

2. **JavaScript Interactivity**:
   - Form validation for email and password fields.
   - Dynamic feedback for user inputs.

---

### Review 3: Advanced Features
#### Console-Based Project
1. **Core Feature Implementation**:
   - Enhanced menu-driven interface for managing cabs and users.
   - Robust error handling for invalid inputs.

2. **Error Handling and Modularity**:
   - Modular code for readability and ease of maintenance.
   - Enhanced error messages for better user experience.

#### GUI-Based Project
1. **Core Features**:
   - GUI-based user registration and profile management using Swing.
   - Event handling for dynamic user interactions.

2. **Error Handling**:
   - Robust input validation and feedback for errors.

#### Maven-Based Project
1. **Servlet Implementation**:
   - Configured servlets with `doGet` and `doPost` methods for user registration and profile management.
   - Integrated JSP pages to display user data dynamically.

2. **JSTL and EL Usage**:
   - Simplifies JSP coding for rendering dynamic content.

#### Spring Boot-Based Project
1. **Controller and Bean Validation**:
   - RESTful APIs for user operations.
   - Validation annotations (e.g., `@NotNull`, `@Email`, `@Size`).

2. **Exception Handling**:
   - Centralized handling using `@ControllerAdvice` and `@ExceptionHandler`.

---

### Review 4: Testing and Documentation
#### Console-Based Project
1. **Code Quality and Features**:
   - Refined modular design for enhanced readability.
   - Added innovative features for better usability.

2. **Documentation**:
   - Comprehensive project documentation.

#### GUI-Based Project
1. **Data Validation**:
   - Ensured robust validation for all user inputs.

2. **Innovative Features**:
   - Added advanced interactivity and customization for GUI elements.

#### Maven-Based Project
1. **Unit Testing**:
   - Tests for service and DAO layers.
   - Edge cases handled in testing.

2. **Final Review and Documentation**:
   - Detailed project structure and usage documentation.

#### Spring Boot-Based Project
1. **Unit Testing**:
   - Comprehensive service-layer testing.

2. **Annotations**:
   - Effective use of annotations for configuration and validation.

---

## Folder and Code Structure
### Console-Based Project
```
OnlineCabBookingSystem/
├── main/
│   └── Main.java
├── model/
│   ├── Cab.java
│   └── User.java
├── service/
    └── CabService.java
```

### GUI-Based Project
```
OnlineCabBookingSystemGUI/
├── src/
│   ├── main/java/
│   │   ├── ui/
│   │   │   └── MainFrame.java
│   │   └── model/
│   │       ├── User.java
│   │       └── Cab.java
│   └── resources/
│       └── icons/
```

### Maven-Based Project
```
src/
├── main/java/
│   ├── servlet/
│   │   ├── UserServlet.java
│   │   └── ProfileServlet.java
│   └── model/
│       ├── User.java
│       └── Cab.java
├── main/webapp/
│   ├── WEB-INF/
│   │   ├── web.xml
│   │   └── jsp/
│   │       ├── register.jsp
│   │       └── profile.jsp
│   ├── css/
│   │   └── styles.css
│   └── js/
│       └── validation.js
```

### Spring Boot-Based Project
```
src/
├── main/java/com/example/cabbooking/
│   ├── controller/
│   │   └── UserController.java
│   ├── exception/
│   │   ├── GlobalExceptionHandler.java
│   │   └── UserNotFoundException.java
│   ├── model/
│   │   └── User.java
│   ├── service/
│   │   └── UserService.java
│   └── CabBookingApplication.java
├── test/java/com/example/cabbooking/
│   └── service/UserServiceTest.java
```

---

## How to Run the Project
1. Clone the repository:
   ```bash
   
   ```

2. **Console-Based Project**:
   - Organize files as per the folder structure.
   - Run `Main.java` in your Java IDE.

3. **GUI-Based Project**:
   - Compile and run `MainFrame.java` in your IDE.

4. **Maven-Based Project**:
   - Build the project using Maven:
     ```bash
     mvn package
     ```
   - Deploy the WAR file to Tomcat.
   - Access the application via `http://localhost:8080`.

5. **Spring Boot-Based Project**:
   - Run the application using:
     ```bash
     mvn spring-boot:run
     ```
   - Test APIs using Postman or any REST client.

---

## Technology Stack
- **Programming Language**: Java
- **Build Tools**: Maven
- **Frameworks**: Spring Boot, JSP, JSTL
- **Frontend**: HTML, CSS, Bootstrap, JavaScript
- **Web Server**: Apache Tomcat
- **GUI**: Swing

---

## Notes
- Ensure correct folder structure to avoid `ClassNotFoundException` errors.
- Validate input data thoroughly to prevent invalid or malicious data entry.

---

## Usage
- **Console-Based Project**: Provides a menu-driven interface to register users and manage cabs.
- **GUI-Based Project**: Features user-friendly forms for managing user profiles.
- **Web-Based Project**: Features user-friendly forms and APIs for managing user data and profiles.

---

## License
This project is open-source and free to use for educational purposes. Appropriate credit must be given to the original author when redistributing or modifying the code.
