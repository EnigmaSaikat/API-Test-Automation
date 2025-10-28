package com.weavers.services;

import com.weavers.config.ConfigReader;
import com.weavers.pojo.LoginAPI.request.LoginRequest;
import com.weavers.pojo.LoginAPI.response.LoginResponse;
//import com.weavers.pojo.LoginAPI.response.LogoutResponse;
import com.weavers.utils.RestUtils;
import com.weavers.utils.TokenManager;
import io.restassured.response.Response;

import java.util.Map;

public class AuthService {

    private static final String BASE_URL = ConfigReader.getBaseUrl();
    private static final String API_VERSION = ConfigReader.getApiVersion();

    private static final String loginEndpoint = BASE_URL + "/" + API_VERSION + ConfigReader.getLoginEndpoint();

    /*
     * Login API
     */
    public static Response login(LoginRequest loginRequest) {
        return RestUtils.postRequest(loginEndpoint, loginRequest);
    }

    /*
     * Login API with wrong Method (GET)
     */
    public static Response wrongMethodLogin() {
        return RestUtils.getRequest(loginEndpoint);
    }

    /*
     * Login and get Response as POJO
     */
    public static LoginResponse loginAndGetResponse(LoginRequest loginRequest) {
        Response response = login(loginRequest);
        return response.as(LoginResponse.class);
    }

    /*
     * Login and store tokens in TokenManager
     */
    public static LoginResponse loginAndStoreToken(LoginRequest loginRequest) {
        LoginResponse loginResponse = loginAndGetResponse(loginRequest);
        TokenManager.getInstance().setTokens(loginResponse);
        System.out.println("✓ Login successful and tokens stored for reuse");
        return loginResponse;
    }

    /*
     * Logout API
     */
//    public static Response logout(String accessToken) {
//        String endpoint = BASE_URL + "/" + API_VERSION + ConfigReader.getLogoutEndpoint();
//        Map<String, String> headers = RestUtils.getBearerTokenHeader(accessToken);
//        return RestUtils.postRequest(endpoint, "", headers);
//    }

    /*
     * Logout and get Response as POJO
     */
//    public static LogoutResponse logoutAndGetResponse(String accessToken) {
//        Response response = logout(accessToken);
//        return response.as(LogoutResponse.class);
//    }

    /*
     * Logout using stored token from TokenManager
     */
//    public static Response logoutWithStoredToken() {
//        String accessToken = TokenManager.getInstance().getAccessToken();
//        return logout(accessToken);
//    }

    /*
     * Get Access Token from Login Response
     */
    public static String getAccessToken(LoginResponse loginResponse) {
        return loginResponse.getData().getToken().getAccess();
    }

    /*
     * Get Refresh Token from Login Response
     */
    public static String getRefreshToken(LoginResponse loginResponse) {
        return loginResponse.getData().getToken().getRefresh();
    }
}