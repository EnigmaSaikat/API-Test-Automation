package com.weavers.base;

import com.weavers.config.ConfigReader;
import com.weavers.utils.LoggerUtils;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {

    @BeforeClass
    public void setupBaseUrl() {
        RestAssured.baseURI = ConfigReader.getBaseUrl();
        LoggerUtils.info("=== Base URL Set: " + RestAssured.baseURI + " ===");
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        LoggerUtils.info("========================================");
        LoggerUtils.info("Starting Test: " + method.getName());
        LoggerUtils.info("========================================");
    }
}