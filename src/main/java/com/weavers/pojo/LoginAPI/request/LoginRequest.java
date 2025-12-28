package com.weavers.pojo.LoginAPI.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public @Data class LoginRequest {
    
    @JsonProperty("email")
    private String email;
    
    @JsonProperty("password")
    private String password;
    
    @JsonProperty("role")
    private String role;

    // Constructors
//    public LoginRequest() {
//    }

//    public LoginRequest(String email, String password, String role) {
//        this.email = email;
//        this.password = password;
//        this.role = role;
//    }

    // Getters and Setters
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    @Override
//    public String toString() {
//        return "LoginRequest{" +
//                "email='" + email + '\'' +
//                ", password='" + password + '\'' +
//                ", role='" + role + '\'' +
//                '}';
//    }
}