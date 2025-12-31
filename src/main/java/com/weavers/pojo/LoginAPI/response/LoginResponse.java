//package com.weavers.pojo.LoginAPI.response;
//
//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.Data;
//
//public @Data class LoginResponse {
//
//    @JsonProperty("code")
//    private int code;
//
//    @JsonProperty("message")
//    private String message;
//
//    @JsonProperty("data")
//    private Data data;
//
//    // Inner Classes
//
//    public @lombok.Data static class Data {
//        @JsonProperty("user")
//        private User user;
//
//        @JsonProperty("token")
//        private Token token;
//
////        public User getUser() {
////            return user;
////        }
////
////        public void setUser(User user) {
////            this.user = user;
////        }
////
////        public Token getToken() {
////            return token;
////        }
////
////        public void setToken(Token token) {
////            this.token = token;
////        }
//    }
//    @JsonIgnoreProperties(ignoreUnknown = true)
//    public @lombok.Data static class User {
//        @JsonProperty("id")
//        private String id;
//
//        @JsonProperty("fullName")
//        private String fullName;
//
//        // @JsonProperty("lastName")
//        // private String lastName;
//
//        @JsonProperty("email")
//        private String email;
//
//        @JsonProperty("phone")
//        private String phone;
//
//        @JsonProperty("isBlocked")
//        private boolean isBlocked;
//
//        @JsonProperty("isVerified")
//        private boolean isVerified;
//
//        @JsonProperty("role")
//        private Role role;
//
//        @JsonProperty("address")
//        private Address address;
//
//        @JsonProperty("profileImage")
//        public String profileImage;
//
//
//
//        // Getters and Setters
////        public String getId() {
////            return id;
////        }
////
////        public void setId(String id) {
////            this.id = id;
////        }
////
////        public String getFirstName() {
////            return fullName;
////        }
////
////        public void setFirstName(String firstName) {
////            this.fullName = firstName;
////        }
//
//        // public String getLastName() {
//        //     return lastName;
//        // }
//
//        // public void setLastName(String lastName) {
//        //     this.lastName = lastName;
//        // }
//
////        public String getEmail() {
////            return email;
////        }
////
////        public void setEmail(String email) {
////            this.email = email;
////        }
////
////        public String getPhone() {
////            return phone;
////        }
////
////        public void setPhone(String phone) {
////            this.phone = phone;
////        }
////
////        public boolean isBlocked() {
////            return isBlocked;
////        }
////
////        public void setBlocked(boolean blocked) {
////            isBlocked = blocked;
////        }
////
////        public boolean isVerified() {
////            return isVerified;
////        }
////
////        public void setVerified(boolean verified) {
////            isVerified = verified;
////        }
////
////        public Role getRole() {
////            return role;
////        }
////
////        public void setRole(Role role) {
////            this.role = role;
////        }
////
////        public Address getAddress() {
////            return address;
////        }
////
////        public void setAddress(Address address) {
////            this.address = address;
////        }
////
////        public String getProfileImage() {
////            return profileImage;
////        }
////
////        public void setProfileImage(String profileImage) {
////            this.profileImage = profileImage;
////        }
////    }
//    @JsonIgnoreProperties(ignoreUnknown = true)
//    public static class Role {
//        @JsonProperty("id")
//        private String id;
//
//        @JsonProperty("name")
//        private String name;
//
////        public String getId() {
////            return id;
////        }
////
////        public void setId(String id) {
////            this.id = id;
////        }
////
////        public String getName() {
////            return name;
////        }
////
////        public void setName(String name) {
////            this.name = name;
////        }
//    }
//@JsonIgnoreProperties(ignoreUnknown = true)
//    public static class Address {
//        @JsonProperty("id")
//        private String id;
//
//        @JsonProperty("country")
//        private String country;
//
//        @JsonProperty("state")
//        private String state;
//
//        @JsonProperty("city")
//        private String city;
//
//        @JsonProperty("zipCode")
//        private String zipCode;
//
////        public String getId() {
////            return id;
////        }
////
////        public void setId(String id) {
////            this.id = id;
////        }
////
////        public String getCountry() {
////            return country;
////        }
////
////        public void setCountry(String country) {
////            this.country = country;
////        }
////
////        public String getState() {
////            return state;
////        }
////
////        public void setState(String state) {
////            this.state = state;
////        }
////
////        public String getCity() {
////            return city;
////        }
////
////        public void setCity(String city) {
////            this.city = city;
////        }
////
////        public String getZipCode() {
////            return zipCode;
////        }
////
////        public void setZipCode(String zipCode) {
////            this.zipCode = zipCode;
////        }
//    }
//@JsonIgnoreProperties(ignoreUnknown = true)
//    public static class Token {
//        @JsonProperty("access")
//        private String access;
//
//        @JsonProperty("refresh")
//        private String refresh;
//
////        public String getAccess() {
////            return access;
////        }
////
////        public void setAccess(String access) {
////            this.access = access;
////        }
////
////        public String getRefresh() {
////            return refresh;
////        }
////
////        public void setRefresh(String refresh) {
////            this.refresh = refresh;
////        }
//    }
//
//    // Main class Getters and Setters
////    public int getCode() {
////        return code;
////    }
////
////    public void setCode(int code) {
////        this.code = code;
////    }
////
////    public String getMessage() {
////        return message;
////    }
////
////    public void setMessage(String message) {
////        this.message = message;
////    }
////
////    public Data getData() {
////        return data;
////    }
////
////    public void setData(Data data) {
////        this.data = data;
//    }
//}


package com.weavers.pojo.LoginAPI.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResponse {

    @JsonProperty("code")
    private int code;

    @JsonProperty("message")
    private String message;

    @JsonProperty("data")
    private LoginPayload data; // Renamed class to avoid confusion, mapped to "data"

    // --- Inner Classes ---

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class LoginPayload { // Changed class name from 'Data' to 'LoginPayload'
        @JsonProperty("user")
        private User user;

        @JsonProperty("token")
        private Token token;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class User {
        @JsonProperty("id")
        private String id;

        @JsonProperty("fullName")
        private String fullName;

        @JsonProperty("email")
        private String email;

        @JsonProperty("phone")
        private String phone;

        @JsonProperty("isBlocked")
        private boolean isBlocked;

        @JsonProperty("isVerified")
        private boolean isVerified;

        @JsonProperty("role")
        private Role role;

        @JsonProperty("address")
        private Address address;

        @JsonProperty("profileImage")
        private String profileImage;
    } // Fixed: This brace was previously commented out!

    @Data // Added: Was missing
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Role {
        @JsonProperty("id")
        private String id;

        @JsonProperty("name")
        private String name;
    }

    @Data // Added: Was missing
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Address {
        @JsonProperty("id")
        private String id;

        @JsonProperty("country")
        private String country;

        @JsonProperty("state")
        private String state;

        @JsonProperty("city")
        private String city;

        @JsonProperty("zipCode")
        private String zipCode;
    }

    @Data // Added: Was missing
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Token {
        @JsonProperty("access")
        private String access;

        @JsonProperty("refresh")
        private String refresh;
    }
}