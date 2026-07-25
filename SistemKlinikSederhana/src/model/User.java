package model;

public class User extends Person {
    private String username;
    private String password;
    private String role;

    public User() {
    }

    public User(int id, String fullName, String username, String password, String role, String phone, String address) {
        super(id, fullName, phone, address);
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean authenticate(String username, String password) {
        return this.username != null && this.username.equalsIgnoreCase(username.trim())
                && this.password != null && this.password.equals(password);
    }

    @Override
    public void displayInfo() {
        System.out.println("User: " + getFullName() + " [" + role + "]");
    }

    public String getUsername() {
        return username == null ? "" : username;
    }

    public void setUsername(String username) {
        this.username = username == null ? "" : username.trim().toLowerCase();
    }

    public String getPassword() {
        return password == null ? "" : password;
    }

    public void setPassword(String password) {
        this.password = password == null ? "" : password;
    }

    public String getRole() {
        return role == null ? "admin" : role;
    }

    public void setRole(String role) {
        this.role = role == null ? "admin" : role.trim();
    }
}
