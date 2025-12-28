package com.weavers.base;

import com.weavers.config.ConfigReader;
import com.weavers.listeners.TestListener;
import com.weavers.utils.LoggerUtils;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.lang.reflect.Method;

@Listeners(TestListener.class)
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