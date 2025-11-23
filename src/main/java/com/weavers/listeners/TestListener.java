package com.weavers.listeners;
import com.weavers.utils.LoggerUtils;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

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