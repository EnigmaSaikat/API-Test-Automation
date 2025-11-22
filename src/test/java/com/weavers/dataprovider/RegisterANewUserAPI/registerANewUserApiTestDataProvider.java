package com.weavers.dataprovider.RegisterANewUserAPI;

import com.weavers.config.ConfigReader;
import com.weavers.pojo.RegisterANewUserAPI.request.RegisterANewUserRequest;
import com.weavers.utils.JsonUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;

public class registerANewUserApiTestDataProvider {

    @DataProvider(name = "validRegisterANewUserData")
    public Object[][] getValidRegisterANewUserData() {
        String filePath = ConfigReader.getRegisterANewUserTestDataPath() + "RegisterANewUserData.json";
        System.out.println(filePath);
        JSONArray registerANewUserArray = JsonUtils.readJsonArray(filePath);

        // Filter only valid register a new user data (you can add a flag in JSON)
        JSONObject validLogin = (JSONObject) registerANewUserArray.get(0);

        RegisterANewUserRequest registerANewUserRequest = new RegisterANewUserRequest();
        registerANewUserRequest.setFullName((String) validLogin.get("fullName"));
        registerANewUserRequest.setEmail((String) validLogin.get("email"));
        registerANewUserRequest.setPassword((String) validLogin.get("password"));
        registerANewUserRequest.setConfirmPassword((String) validLogin.get("confirmPassword"));
        registerANewUserRequest.setToken((String) validLogin.get("token"));
        registerANewUserRequest.setRole((String) validLogin.get("role"));
        return new Object[][]{{registerANewUserRequest}};
    }
}
