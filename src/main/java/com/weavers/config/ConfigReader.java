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

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }

    public static String getBaseUrl() {
        return properties.getProperty("BASE_URL");
    }

    public static String getApiVersion() {
        return properties.getProperty("API_VERSION");
    }

    public static String getLoginEndpoint() { return properties.getProperty("LOGIN_ENDPOINT");}

    public static String getAdminAddCategory() {
        return properties.getProperty("ADD_CATEGORY_ENDPOINT");
    }
    



    public static String getLogoutEndpoint() {
        return properties.getProperty("LOGOUT_ENDPOINT");
    }

    public static String getLoginTestDataPath() {
        return properties.getProperty("LOGIN_TEST_DATA_PATH");
    }
}