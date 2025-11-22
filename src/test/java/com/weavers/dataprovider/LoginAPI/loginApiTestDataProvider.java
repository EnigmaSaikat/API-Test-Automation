package com.weavers.dataprovider.LoginAPI;

import com.weavers.config.ConfigReader;
import com.weavers.pojo.LoginAPI.request.LoginRequest;
import com.weavers.utils.JsonUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;

public class loginApiTestDataProvider {

    @DataProvider(name = "loginData")
    public Object[][] getLoginData() {
        String filePath = ConfigReader.getLoginTestDataPath() + "loginData.json";
        JSONArray loginArray = JsonUtils.readJsonArray(filePath);

        Object[][] data = new Object[loginArray.size()][1];

        for (int i = 0; i < loginArray.size(); i++) {
            JSONObject loginObj = (JSONObject) loginArray.get(i);

            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setEmail((String) loginObj.get("email"));
            loginRequest.setPassword((String) loginObj.get("password"));
            loginRequest.setRole((String) loginObj.get("role"));

            data[i][0] = loginRequest;
        }

        return data;
    }

    @DataProvider(name = "validLoginData")
    public Object[][] getValidLoginData() {
        String filePath = ConfigReader.getLoginTestDataPath() + "loginData.json";
        JSONArray loginArray = JsonUtils.readJsonArray(filePath);

        // Filter only valid login data (you can add a flag in JSON)
        JSONObject validLogin = (JSONObject) loginArray.get(0);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail((String) validLogin.get("email"));
        loginRequest.setPassword((String) validLogin.get("password"));
        loginRequest.setRole((String) validLogin.get("role"));

        return new Object[][]{{loginRequest}};
    }

    @DataProvider(name = "loginInvalidTestData")
    public Object[][] getLoginInvalidTestData() {
        String filePath = ConfigReader.getLoginTestDataPath() + "invalidLoginData.json";
        JSONArray loginArray = JsonUtils.readJsonArray(filePath);

        Object[][] data = new Object[loginArray.size()][1];

        for (int i = 0; i < loginArray.size(); i++) {
            JSONObject loginObj = (JSONObject) loginArray.get(i);

            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setEmail((String) loginObj.get("email"));
            loginRequest.setPassword((String) loginObj.get("password"));
            loginRequest.setRole((String) loginObj.get("role"));

            data[i][0] = loginRequest;
        }

        return data;
    }

    @DataProvider(name = "LoginMissingFieldsTestData")
    public Object[][] getLoginMissingFieldsTestData() {
        String filePath = ConfigReader.getLoginTestDataPath() + "loginDataWithMissingFields.json";
        JSONArray loginArray = JsonUtils.readJsonArray(filePath);

        Object[][] data = new Object[loginArray.size()][1];

        for (int i = 0; i < loginArray.size(); i++) {
            JSONObject loginObj = (JSONObject) loginArray.get(i);

            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setEmail((String) loginObj.get("email"));
            loginRequest.setPassword((String) loginObj.get("password"));
            loginRequest.setRole((String) loginObj.get("role"));

            data[i][0] = loginRequest;
        }

        return data;
    }

    @DataProvider(name = "invalidEmailFormatLoginData")
    public Object[][] getInvalidEmailFormatLoginData() {
        String filePath = ConfigReader.getLoginTestDataPath() + "invalidEmailFormat.json";
        JSONArray loginArray = JsonUtils.readJsonArray(filePath);

        // Filter only valid login data (you can add a flag in JSON)
        JSONObject validLogin = (JSONObject) loginArray.get(0);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail((String) validLogin.get("email"));
        loginRequest.setPassword((String) validLogin.get("password"));
        loginRequest.setRole((String) validLogin.get("role"));

        return new Object[][]{{loginRequest}};
    }

    @DataProvider(name = "EmptyJsonLoginData")
    public Object[][] getEmptyJsonLoginData() {
        String filePath = ConfigReader.getLoginTestDataPath() + "emptyBody.json";
        JSONArray loginArray = JsonUtils.readJsonArray(filePath);

        // Filter only valid login data (you can add a flag in JSON)
        JSONObject validLogin = (JSONObject) loginArray.get(0);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail((String) validLogin.get("email"));
        loginRequest.setPassword((String) validLogin.get("password"));
        loginRequest.setRole((String) validLogin.get("role"));

        return new Object[][]{{loginRequest}};
    }


    @DataProvider(name = "LoginJsonWithVeryLoginData")
    public Object[][] getLoginJsonWithVeryLoginData() {
        String filePath = ConfigReader.getLoginTestDataPath() + "longEmailAndPasswordData.json";
        JSONArray loginArray = JsonUtils.readJsonArray(filePath);

        // Filter only valid login data (you can add a flag in JSON)
        JSONObject validLogin = (JSONObject) loginArray.get(0);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail((String) validLogin.get("email"));
        loginRequest.setPassword((String) validLogin.get("password"));
        loginRequest.setRole((String) validLogin.get("role"));

        return new Object[][]{{loginRequest}};
    }

}