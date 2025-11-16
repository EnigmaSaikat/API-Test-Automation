package com.weavers.services;

import com.weavers.config.ConfigReader;
import com.weavers.pojo.LoginAPI.request.LoginRequest;
import com.weavers.pojo.LoginAPI.response.LoginResponse;
//import com.weavers.pojo.LoginAPI.response.LogoutResponse;
import com.weavers.pojo.RegisterANewUserAPI.request.RegisterANewUserRequest;
import com.weavers.pojo.RegisterANewUserAPI.response.RegisterANewUserResponse;
import com.weavers.pojo.RequestOTPAPI.request.RequestOTPRequest;
import com.weavers.pojo.RequestOTPAPI.response.RequestOTPResponse;
import com.weavers.utils.RestUtils;
import com.weavers.utils.TokenManager;
import io.restassured.response.Response;

import java.util.Map;

public class AuthService {

    private static final String BASE_URL = ConfigReader.getBaseUrl();
    private static final String API_VERSION = ConfigReader.getApiVersion();
    private static final String AUTH_SERVICES = ConfigReader.getAuthServices();

    private static final String loginEndpoint = BASE_URL + "/" + API_VERSION + "/" + AUTH_SERVICES + ConfigReader.getLoginEndpoint();
    private static final String registerEndpoint = BASE_URL + "/" + API_VERSION + "/" + AUTH_SERVICES + ConfigReader.getRegisterEndpoint();
    private static final String requestOTPEndpoint = BASE_URL + "/" + API_VERSION + "/" + AUTH_SERVICES + ConfigReader.getRequestOTP();

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
        System.out.println("Login successful and tokens stored for reuse");
        return loginResponse;
    }

    /*
    * Register new user API
    */
    public static Response registerNewUser(RegisterANewUserRequest registerANewUserRequest) {
        return RestUtils.postRequest(registerEndpoint, registerANewUserRequest);
    }

    /*
     * Register a new user Response as POJO
     */
    public static RegisterANewUserResponse RegisterANewUserAndGetResponse(RegisterANewUserRequest registerANewUserRequest) {
        Response response = registerNewUser(registerANewUserRequest);
        return response.as(RegisterANewUserResponse.class);
    }

    /*
     * Request OTP API
     */
    public static Response requestOTP(RequestOTPRequest requestOTPRequest) {
        return RestUtils.postRequest(requestOTPEndpoint, requestOTPRequest);
    }

    /*
     * Request OTP Response as POJO
     */
    public static RequestOTPResponse RequestOTPAndGetResponse(RequestOTPRequest requestOTPRequest) {
        Response response = requestOTP(requestOTPRequest);
        return response.as(RequestOTPResponse.class);
    }

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