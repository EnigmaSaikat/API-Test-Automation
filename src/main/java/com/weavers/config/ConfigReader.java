package com.weavers.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static final Properties properties;
    private static final String CONFIG_FILE_PATH = "src/test/resources/config.properties";

    static {
        try {
            FileInputStream fis = new FileInputStream(CONFIG_FILE_PATH);
            properties = new Properties();
            properties.load(fis);
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties file");
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("BASE_URL");
    }
    public static String getApiVersion() {
        return properties.getProperty("API_VERSION");
    }
    public static String getAuthServices() { return properties.getProperty("AUTH_SERVICES"); }


    public static String getLoginEndpoint() { return properties.getProperty("LOGIN_ENDPOINT");}
    public static String getRegisterEndpoint() {
        return properties.getProperty("REGISTER_ENDPOINT");
    }
    public static String getRequestOTP() {
        return properties.getProperty("REQUEST_OTP_ENDPOINT");
    }


    public static String getLoginTestDataPath() { return properties.getProperty("LOGIN_TEST_DATA_PATH");}
    public static String getRegisterANewUserTestDataPath() { return properties.getProperty("REGISTER_A_NEW_USER_DATA_PATH");}
    public static String getRequestOTPDataPath() { return properties.getProperty("REQUEST_OTP_TEST_DATA_PATH");}
}