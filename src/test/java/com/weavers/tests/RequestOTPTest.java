package com.weavers.tests;

import com.weavers.base.BaseTest;
import com.weavers.dataprovider.RequestOTPAPI.requestOTPApiTestDataProvider;
import com.weavers.pojo.RequestOTPAPI.request.RequestOTPRequest;
import com.weavers.pojo.RequestOTPAPI.response.RequestOTPResponse;
import com.weavers.services.AuthService;
import com.weavers.utils.MyRetry;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Authentication")
@Feature("Request OTP API")
public class RequestOTPTest extends BaseTest {
    @Story("Successful Request OTP Test Flow")
    @Severity(SeverityLevel.BLOCKER)
    @Description("This test case validates the Request OTP API with valid data")
    @TmsLink("TC-REQOTP-001")
    @Issue("AUTH-256")
    @Test(description = "Validating the Request OTP data", dataProvider = "validRequestOTPData", dataProviderClass = requestOTPApiTestDataProvider.class, priority = 1, retryAnalyzer = MyRetry.class)
    public void testRequestOTP(RequestOTPRequest requestOTPRequest) {
       System.out.println("Testing Login with: " + requestOTPRequest);
        Response response = AuthService.requestOTP(requestOTPRequest);

        Assert.assertEquals(response.getStatusCode(), 200);

        RequestOTPResponse requestOTPResponse = response.as(RequestOTPResponse.class);
        Assert.assertEquals(requestOTPResponse.getCode(), 200, "Response code should be 200");
        Assert.assertEquals(requestOTPResponse.getMessage(), "OTP sent successfully", "Message mismatch");
        Assert.assertTrue(requestOTPResponse.isData(), "Data field should be true");
    }
}
