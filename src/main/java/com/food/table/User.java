package main.java.com.food.table;

public class User {
    private int userId;
    private String name;
    private String phoneNumber;
    private String preferences;
    private String allergies;
    private String email;
    private String password;

    // Default Constructor
    public User() {
    }

    // Parameterized Constructor
    public User(int userId, String name, String phoneNumber, String preferences, String allergies, String email, String password) {
        this.userId = userId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.preferences = preferences;
        this.allergies = allergies;
        this.email = email;
        this.password = password;
    }

    // Getters
    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getPreferences() {
        return preferences;
    }

    public String getAllergies() {
        return allergies;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}