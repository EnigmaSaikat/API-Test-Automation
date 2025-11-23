package com.weavers.utils;

import com.weavers.pojo.LoginAPI.response.LoginResponse;


public class TokenManager {

    private static TokenManager instance;
    private final ThreadLocal<String> accessToken = new ThreadLocal<>();
    private final ThreadLocal<String> refreshToken = new ThreadLocal<>();
    private final ThreadLocal<LoginResponse> loginResponse = new ThreadLocal<>();
    private final ThreadLocal<Long> tokenCreatedTime = new ThreadLocal<>();

    private TokenManager() {
    }

    public static synchronized TokenManager getInstance() {
        if (instance == null) {
            instance = new TokenManager();
        }
        return instance;
    }

    public void setTokens(LoginResponse response) {
        // Store the full response object
        this.loginResponse.set(response);

        if (response != null && response.getData() != null && response.getData().getToken() != null) {
            // Store specific tokens
            this.accessToken.set(response.getData().getToken().getAccess());
            this.refreshToken.set(response.getData().getToken().getRefresh());
            this.tokenCreatedTime.set(System.currentTimeMillis());

            LoggerUtils.info("===== Tokens stored in TokenManager for Thread: " + Thread.currentThread().getId()+" =====");        } else {
            LoggerUtils.error("===== Failed to store tokens: LoginResponse or Data was null =====");
        }
    }

    public void setTokens(String access, String refresh) {
        this.accessToken.set(access);
        this.refreshToken.set(refresh);
        this.tokenCreatedTime.set(System.currentTimeMillis());
        LoggerUtils.info("✓ Tokens manually stored for Thread: " + Thread.currentThread().getId());
    }

    public String getAccessToken() {
        if (accessToken.get() == null) {
            LoggerUtils.error("===== Access Token is missing in TokenManager. =====");
            // Optionally throw exception if you want to fail fast
            // throw new RuntimeException("Access Token is not available.");
        }
        return accessToken.get();
    }

    public String getRefreshToken() {
        if (refreshToken.get() == null) {
            LoggerUtils.warn("===== Refresh Token requested but not found. =====");
        }
        return refreshToken.get();
    }

    public LoginResponse getLoginResponse() {
        return loginResponse.get();
    }

    public boolean hasToken() {
        return accessToken.get() != null && !accessToken.get().isEmpty();
    }

    public boolean isTokenExpired(long durationInMillis) {
        if (!hasToken()) {
            LoggerUtils.debug("===== Check failed: No token found in manager. =====");
            return true;
        }

        if (tokenCreatedTime.get() == null) {
            return true;
        }

        long currentTime = System.currentTimeMillis();
        long diff = currentTime - tokenCreatedTime.get();

        LoggerUtils.debug("===== Token Age: " + diff + "ms | Max Duration: " + durationInMillis + "ms" + " =====");

        return diff > durationInMillis;
    }

    public void clearTokens() {
        accessToken.remove();
        refreshToken.remove();
        loginResponse.remove();
        tokenCreatedTime.remove();

        LoggerUtils.info("===== Tokens cleared from TokenManager =====");
    }

    public String getBearerTokenHeader() {
        return "Bearer " + getAccessToken();
    }

    public void printTokenInfo() {
        String access = accessToken.get();
        String refresh = refreshToken.get();

        LoggerUtils.debug("===== Token Info (Thread " + Thread.currentThread().getId() + ") =====");
        LoggerUtils.debug("Access Token: " + (access != null ? access.substring(0, Math.min(10, access.length())) + "..." : "null"));
        LoggerUtils.debug("Refresh Token: " + (refresh != null ? refresh.substring(0, Math.min(10, refresh.length())) + "..." : "null"));
        LoggerUtils.debug("Has Token: " + hasToken());
        LoggerUtils.debug("==================================");
    }
}