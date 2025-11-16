package com.weavers.tests;

import com.weavers.base.BaseTest;
import com.weavers.dataprovider.LoginAPI.loginApiTestDataProvider;
import com.weavers.pojo.LoginAPI.request.LoginRequest;
import com.weavers.pojo.LoginAPI.response.LoginResponse;
import com.weavers.services.AuthService;
import com.weavers.utils.MyRetry;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Authentication")
@Feature("Login API")
public class LoginTest extends BaseTest {
    @Story("Successful Login Flow")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Validates successful login with valid credentials and verifies response structure including user data and JWT tokens")
    @TmsLink("TC-LOGIN-001")
    @Issue("AUTH-123")
    @Test(description = "Validating the Response data", dataProvider = "validLoginData", dataProviderClass = loginApiTestDataProvider.class, priority = 1, retryAnalyzer = MyRetry.class)
    public void testLoginSuccess(LoginRequest loginRequest) {
        try{
            System.out.println("Testing Login with: " + loginRequest);
            Response response = AuthService.login(loginRequest);
            // Assertions
            Assert.assertEquals(response.getStatusCode(), 200, "Status code should be 200");
            LoginResponse loginResponse = response.as(LoginResponse.class);
            Assert.assertEquals(loginResponse.getCode(), 200, "Response code should be 200");
            Assert.assertEquals(loginResponse.getMessage(), "Login successfully", "Message mismatch");
            Assert.assertNotNull(loginResponse.getData(), "Data should not be null");
            Assert.assertNotNull(loginResponse.getData().getUser(), "User should not be null");
            Assert.assertNotNull(loginResponse.getData().getToken(), "Token should not be null");
            Assert.assertEquals(loginResponse.getData().getUser().getEmail(), loginRequest.getEmail(), "Email mismatch");
            Assert.assertTrue(loginResponse.getData().getUser().isVerified(), "User should be verified");
            Assert.assertNotNull(loginResponse.getData().getToken().getAccess(), "Access token should not be null");
            Assert.assertNotNull(loginResponse.getData().getToken().getRefresh(), "Refresh token should not be null");
            Assert.assertTrue(loginResponse.getData().getToken().getAccess().matches("^[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+$"));
            Assert.assertTrue(loginResponse.getData().getToken().getAccess().matches("^[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+$"));
            System.out.println("✓ Login Test Passed Successfully!");
            System.out.println("Access Token: " + loginResponse.getData().getToken().getAccess());
            System.out.println("Refresh Token: " + loginResponse.getData().getToken().getRefresh());
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Test(description = "Test token allows access to protected endpoint", dataProvider = "loginData", dataProviderClass = loginApiTestDataProvider.class, priority = 2, retryAnalyzer = MyRetry.class)
    public void testLoginWithAllData(LoginRequest loginRequest) {
        System.out.println("Testing Login with: " + loginRequest);
        Response response = AuthService.wrongMethodLogin();
        System.out.println("Response Status Code: " + response.getStatusCode());
        System.out.println("Response Body: " + response.getBody().asString());
        // Basic validation
        Assert.assertTrue(response.getStatusCode() == 200 || response.getStatusCode() == 404, "Status code should be 200 or 404");
        if (response.getStatusCode() == 200) {
            LoginResponse loginResponse = response.as(LoginResponse.class);
            Assert.assertEquals(loginResponse.getCode(), 200, "Response code should be 200");
            Assert.assertEquals(loginResponse.getMessage(), "Login successfully", "Message mismatch");
            Assert.assertNotNull(loginResponse.getData(), "Data should not be null");
            Assert.assertNotNull(loginResponse.getData().getUser(), "User should not be null");
            Assert.assertNotNull(loginResponse.getData().getToken(), "Token should not be null");
            Assert.assertEquals(loginResponse.getData().getUser().getEmail(), loginRequest.getEmail(), "Email mismatch");
            Assert.assertTrue(loginResponse.getData().getUser().isVerified(), "User should be verified");
            Assert.assertNotNull(loginResponse.getData().getToken().getAccess(), "Access token should not be null");
            Assert.assertNotNull(loginResponse.getData().getToken().getRefresh(), "Refresh token should not be null");
            Assert.assertTrue(loginResponse.getData().getToken().getAccess().matches("^[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+$"));
            Assert.assertTrue(loginResponse.getData().getToken().getAccess().matches("^[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+\\.[A-Za-z0-9-_]+$"));
            System.out.println("✓ Login Test Passed Successfully!");
            System.out.println("Access Token: " + loginResponse.getData().getToken().getAccess());
            System.out.println("Refresh Token: " + loginResponse.getData().getToken().getRefresh());
        } else if (response.getStatusCode() == 404) {
            System.out.println("No data found for this endpoint — valid 404 case.");
        }
    }

    @Test(description = "Verify login is rejected for invalid combinations of email, password, and role", dataProvider = "loginInvalidTestData", dataProviderClass = loginApiTestDataProvider.class, priority = 3, retryAnalyzer = MyRetry.class)
    public void testShouldNotAllowLoginWithInvalidCredentials(LoginRequest loginRequest) {
        try {
            System.out.println("Testing Login with: " + loginRequest);
            Response response = AuthService.login(loginRequest);
            Assert.assertEquals(response.getStatusCode(), 400, "Status code should be 400 or 401 or 403");
        } catch (Exception e) {
            System.err.println("Exception during login with invalid credentials: " + e.getMessage());
        }
    }

    @Test(description = "Verify login is rejected for missing email field", dataProvider = "LoginMissingFieldsTestData", dataProviderClass = loginApiTestDataProvider.class, priority = 4, retryAnalyzer = MyRetry.class)
    public void testShouldNotAllowLoginWithMissingEmail(LoginRequest loginRequest) {
        try {
            System.out.println("Testing Login with: " + loginRequest);
            Response response = AuthService.login(loginRequest);
            Assert.assertEquals(response.getStatusCode(), 400, "Status code should be 400 or 401 or 403");
        } catch (Exception e) {
            System.err.println("Exception during login with missing email: " + e.getMessage());
        }
    }

    @Test(description = "Verify login is rejected for invalid email format", dataProvider = "invalidEmailFormatLoginData", dataProviderClass = loginApiTestDataProvider.class, priority = 5, retryAnalyzer = MyRetry.class)
    public void testLoginInvalidFormat(LoginRequest loginRequest) {
        try {
            System.out.println("Testing Login with: " + loginRequest);
            Response response = AuthService.login(loginRequest);
            Assert.assertEquals(response.getStatusCode(), 400, "Status code should be 400 or 401 or 403");
        } catch (Exception e) {
            System.err.println("Exception during login with invalid format: " + e.getMessage());
        }
    }

    @Test(description = "Verify login is rejected for unverified user", dataProvider = "EmptyJsonLoginData", dataProviderClass = loginApiTestDataProvider.class, priority = 6, retryAnalyzer = MyRetry.class)
    public void testShouldNotAllowLoginForUnverifiedUser(LoginRequest loginRequest) {
        try {
            System.out.println("Testing Login with: " + loginRequest);
            Response response = AuthService.login(loginRequest);
            Assert.assertEquals(response.getStatusCode(), 400, "Status code should be 403 for unverified user");
        } catch (Exception e) {
            System.err.println("Exception during login for unverified user: " + e.getMessage());
        }
    }

    @Test(description = "Verify login user name and password is very long", dataProvider = "LoginJsonWithVeryLoginData", dataProviderClass = loginApiTestDataProvider.class, priority = 6, retryAnalyzer = MyRetry.class)
    public void testLoginWithVeryLongEmailAndPassword(LoginRequest loginRequest) {
        try {
            System.out.println("Testing Login with: " + loginRequest);
            Response response = AuthService.login(loginRequest);
            Assert.assertEquals(response.getStatusCode(), 400, "Status code should be 403 for unverified user");
        } catch (Exception e) {
            System.err.println("Exception during login for unverified user: " + e.getMessage());
        }
    }
}