package com.inventory.utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Custom TestNG Listener for enhanced reporting.
 * Developed by: IT23241732
 */
public class TestNGListener implements ITestListener {

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("Test Starting: " + result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Test reporting implementation - IT23241732
        System.out.println("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Test reporting implementation - IT23241732
        System.out.println("Test Failed: " + result.getName());
        System.out.println("Error: " + result.getThrowable().getMessage());
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Starting Test Suite: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Finished Test Suite: " + context.getName());
    }
}
