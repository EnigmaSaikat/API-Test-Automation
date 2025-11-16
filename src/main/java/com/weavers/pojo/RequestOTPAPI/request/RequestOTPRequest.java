package com.weavers.pojo.RequestOTPAPI.request;

public class RequestOTPRequest {

    private String email;

    public RequestOTPRequest() {}

    public RequestOTPRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RequestOTPRequest{" + "email=" + email + '}';
    }
}