package com.weavers.tests;

import com.weavers.config.ConfigReader;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.lang.reflect.Method;

public class BaseTest {

    @BeforeClass
    public void setupBaseUrl() {
        RestAssured.baseURI = ConfigReader.getBaseUrl();
        System.out.println("=== Base URL Set: " + RestAssured.baseURI + " ===");
    }

    @BeforeMethod
    public void beforeMethod(Method method) {
        System.out.println("\n========================================");
        System.out.println("Starting Test: " + method.getName());
        System.out.println("========================================");
    }
}