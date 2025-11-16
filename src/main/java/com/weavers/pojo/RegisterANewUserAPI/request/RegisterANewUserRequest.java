package com.weavers.pojo.RegisterANewUserAPI.request;

public class RegisterANewUserRequest {
    private String fullName;
    private String email;
    private String password;
    private String confirmPassword;
    private String token;
    private String role;

    // Default constructor
    public RegisterANewUserRequest() {
    }

    // Parameterized constructor
    public RegisterANewUserRequest(String fullName, String email, String password,
                            String confirmPassword, String token, String role) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.token = token;
        this.role = role;
    }

    // Getters
    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

    // Setters
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // toString method
    @Override
    public String toString() {
        return "UserRegistration{" +
                "fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                ", confirmPassword='[PROTECTED]'" +
                ", token='" + token + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
