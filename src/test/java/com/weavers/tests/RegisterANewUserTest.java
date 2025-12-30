package com.weavers.tests;

import com.weavers.base.BaseTest;
import com.weavers.dataprovider.RegisterANewUserAPI.registerANewUserApiTestDataProvider;
import com.weavers.pojo.RegisterANewUserAPI.request.RegisterANewUserRequest;
import com.weavers.services.AuthService;
import com.weavers.utils.MyRetry;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

@Epic("Authentication")
@Feature("Register A New User API")
public class RegisterANewUserTest extends BaseTest {
    @Story("Successful Register A New User Flow")
    @Severity(SeverityLevel.BLOCKER)
    @Description("This test case validates the Register A New User API with valid data")
    @TmsLink("TC-LOGIN-001")
    @Issue("AUTH-123")
    @Test(description = "Validating the Response data", dataProvider = "validRegisterANewUserData", dataProviderClass = registerANewUserApiTestDataProvider.class, priority = 1 )
    public void testRegisterANewUser(RegisterANewUserRequest registerANewUserRequest) {
        try {
            System.out.println("Testing Login with: " + registerANewUserRequest);
            Response response = AuthService.registerNewUser(registerANewUserRequest);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
