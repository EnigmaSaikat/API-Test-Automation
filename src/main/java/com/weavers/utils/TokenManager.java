package com.weavers.utils;

import com.weavers.pojo.LoginAPI.response.LoginResponse;

public class TokenManager {

    private static TokenManager instance;
    private String accessToken;
    private String refreshToken;
    private LoginResponse loginResponse;
    private long tokenCreatedTime;

    // Private constructor for Singleton
    private TokenManager() {
    }

    public static TokenManager getInstance() {
        if (instance == null) {
            synchronized (TokenManager.class) {
                if (instance == null) {
                    instance = new TokenManager();
                }
            }
        }
        return instance;
    }

    public void setTokens(LoginResponse loginResponse) {
        this.loginResponse = loginResponse;
        if (loginResponse != null && loginResponse.getData() != null
                && loginResponse.getData().getToken() != null) {
            this.accessToken = loginResponse.getData().getToken().getAccess();
            this.refreshToken = loginResponse.getData().getToken().getRefresh();
            this.tokenCreatedTime = System.currentTimeMillis();
            System.out.println("Tokens stored in TokenManager");
        }
    }

    public void setTokens(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.tokenCreatedTime = System.currentTimeMillis();
        System.out.println("✓ Tokens manually stored in TokenManager");
    }

    public String getAccessToken() {
        if (accessToken == null || accessToken.isEmpty()) {
            throw new RuntimeException("Access Token is not available. Please login first.");
        }
        return accessToken;
    }

    public String getRefreshToken() {
        if (refreshToken == null || refreshToken.isEmpty()) {
            throw new RuntimeException("Refresh Token is not available. Please login first.");
        }
        return refreshToken;
    }

    /*
     * Get complete LoginResponse
     */
    public LoginResponse getLoginResponse() {
        return loginResponse;
    }


    public boolean hasToken() {
        return accessToken != null && !accessToken.isEmpty();
    }

    public boolean isTokenExpired(long durationInMillis) {
        if (!hasToken()) {
            return true;
        }
        long currentTime = System.currentTimeMillis();
        return (currentTime - tokenCreatedTime) > durationInMillis;
    }

    public void clearTokens() {
        this.accessToken = null;
        this.refreshToken = null;
        this.loginResponse = null;
        this.tokenCreatedTime = 0;
        System.out.println("✓ Tokens cleared from TokenManager");
    }

    public String getBearerTokenHeader() {
        return "Bearer " + getAccessToken();
    }

    public void printTokenInfo() {
        System.out.println("===== Token Info =====");
        System.out.println("Access Token: " + (accessToken != null ? accessToken.substring(0, Math.min(20, accessToken.length())) + "..." : "null"));
        System.out.println("Refresh Token: " + (refreshToken != null ? refreshToken.substring(0, Math.min(20, refreshToken.length())) + "..." : "null"));
        System.out.println("Token Created: " + tokenCreatedTime);
        System.out.println("Has Token: " + hasToken());
        System.out.println("=====================");
    }
}