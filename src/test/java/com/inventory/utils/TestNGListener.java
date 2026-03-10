package com.inventory.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

/**
 * Enhanced Custom TestNG Listener using ExtentReports for Premium Visuals.
 * Developed by: IT23241732
 */
public class TestNGListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        // Initialize Extent Spark Reporter - IT23241732
        ExtentSparkReporter spark = new ExtentSparkReporter("target/extent-reports/PremiumTestReport.html");
        spark.config().setTheme(Theme.DARK);
        spark.config().setDocumentTitle("Business Management System - QA Report");
        spark.config().setReportName("Inventory System Test Execution Results");

        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Company", "SLIIT - SEPQM");
        extent.setSystemInfo("Project", "Inventory Management System");
        extent.setSystemInfo("Tester", "IT23241732");

        System.out.println("Starting Premium Test Suite: " + context.getName());
    }

    @Override
    public void onTestStart(ITestResult result) {
        // Create a new test in the report - IT23241732
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        test.set(extentTest);
        test.get().log(Status.INFO, "Test Execution Started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Log success - IT23241732
        test.get().log(Status.PASS, "Test Passed Successfully");
        System.out.println("Test Passed: " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Log failure with details - IT23241732
        test.get().log(Status.FAIL, "Test Failed");
        test.get().fail(result.getThrowable());
        System.out.println("Test Failed: " + result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.get().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onFinish(ITestContext context) {
        // Flush report to file - IT23241732
        extent.flush();
        System.out.println("Finished Premium Test Suite: " + context.getName());
        System.out.println("Premium Report Generated: target/extent-reports/PremiumTestReport.html");
    }
}
