package com.weavers.dataprovider.RequestOTPAPI;

import com.weavers.config.ConfigReader;
import com.weavers.pojo.RequestOTPAPI.request.RequestOTPRequest;
import com.weavers.utils.JsonUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;

public class requestOTPApiTestDataProvider {

    @DataProvider(name = "validRequestOTPData")
    public Object[][] getValidRequestOTPData() {
        String filePath = ConfigReader.getRequestOTPDataPath() + "RequestOTPData.json";
        System.out.println("Reading Request OTP test data from: " + filePath);
        JSONArray requestOTPArray = JsonUtils.readJsonArray(filePath);
        JSONObject validOtp = (JSONObject) requestOTPArray.get(0);

        RequestOTPRequest requestOTPRequest = new RequestOTPRequest();
        requestOTPRequest.setEmail((String) validOtp.get("email"));

        return new Object[][]{{requestOTPRequest}};
    }
}
