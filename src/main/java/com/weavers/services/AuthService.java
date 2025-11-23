package com.weavers.services;

import com.weavers.endpoints.Routes;
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



public class AuthService {

    public static Response login(LoginRequest loginRequest) {
        return RestUtils.postRequest(Routes.LOGIN_URI, loginRequest);
    }

    public static Response wrongMethodLogin() {
        return RestUtils.getRequest(Routes.LOGIN_URI);
    }

    public static LoginResponse loginAndGetResponse(LoginRequest loginRequest) {
        Response response = login(loginRequest);
        return response.as(LoginResponse.class);
    }

    public static LoginResponse loginAndStoreToken(LoginRequest loginRequest) {
        LoginResponse loginResponse = loginAndGetResponse(loginRequest);
        TokenManager.getInstance().setTokens(loginResponse);
        System.out.println("Login successful and tokens stored for reuse");
        return loginResponse;
    }

    public static Response registerNewUser(RegisterANewUserRequest registerANewUserRequest) {
        return RestUtils.postRequest(Routes.REGISTER_URI, registerANewUserRequest);
    }

    public static RegisterANewUserResponse RegisterANewUserAndGetResponse(RegisterANewUserRequest registerANewUserRequest) {
        Response response = registerNewUser(registerANewUserRequest);
        return response.as(RegisterANewUserResponse.class);
    }

    public static Response requestOTP(RequestOTPRequest requestOTPRequest) {
        return RestUtils.postRequest(Routes.REQUEST_OTP_URI, requestOTPRequest);
    }

    public static RequestOTPResponse RequestOTPAndGetResponse(RequestOTPRequest requestOTPRequest) {
        Response response = requestOTP(requestOTPRequest);
        return response.as(RequestOTPResponse.class);
    }

    public static String getAccessToken(LoginResponse loginResponse) {
        return loginResponse.getData().getToken().getAccess();
    }

    public static String getRefreshToken(LoginResponse loginResponse) {
        return loginResponse.getData().getToken().getRefresh();
    }
}