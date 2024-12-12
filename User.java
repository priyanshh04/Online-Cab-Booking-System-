package model;

public class User {
    private String userId;
    private String name;
    private String contact;

    public User(String userId, String name, String contact) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (contact == null || contact.isEmpty()) {
            throw new IllegalArgumentException("Contact cannot be null or empty");
        }
        this.userId = userId;
        this.name = name;
        this.contact = contact;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        if (userId == null || userId.isEmpty()) {
            throw new IllegalArgumentException("User ID cannot be null or empty");
        }
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        if (contact == null || contact.isEmpty()) {
            throw new IllegalArgumentException("Contact cannot be null or empty");
        }
        if (!contact.matches("\\d{10}")) {
            throw new IllegalArgumentException("Invalid contact format. Must be a 10-digit number.");
        }
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                '}';
    }
}
