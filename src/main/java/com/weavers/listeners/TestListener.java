package com.weavers.listeners;
import com.weavers.utils.LoggerUtils;
import com.weavers.utils.MyRetry;
import org.testng.IAnnotationTransformer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class TestListener implements ITestListener, IAnnotationTransformer {

    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod){
        annotation.setRetryAnalyzer(MyRetry.class);
    }

    @Override
    public void onStart(ITestContext context) {
        LoggerUtils.info("========================================");
        LoggerUtils.info("Test Suite Started: " + context.getName());
        LoggerUtils.info("========================================");
        
    }

    @Override
    public void onFinish(ITestContext context) {
        LoggerUtils.info("========================================");
        LoggerUtils.info("Test Suite Finished: " + context.getName());
        LoggerUtils.info("========================================");
       
    }

    @Override
    public void onTestStart(ITestResult result) {
        LoggerUtils.info("========================================");
        LoggerUtils.startTestCase(result.getMethod().getMethodName());
        LoggerUtils.info("========================================");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LoggerUtils.info("Test Passed: " + result.getMethod().getMethodName());
        LoggerUtils.endTestCase(result.getMethod().getMethodName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        LoggerUtils.error("Test Failed: " + result.getMethod().getMethodName(), result.getThrowable());
        LoggerUtils.error("Reason: ", result.getThrowable());
        LoggerUtils.endTestCase(result.getMethod().getMethodName());
       
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LoggerUtils.warn("Test Skipped: " + result.getName());
        if (result.getThrowable() != null) {
            LoggerUtils.warn("Reason: " + result.getThrowable().getMessage());
        }
    }
}