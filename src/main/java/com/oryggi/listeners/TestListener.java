package com.oryggi.listeners;

import com.oryggi.utils.ScreenshotUtil;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.openqa.selenium.WebDriver;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        try {
            Object testClass = result.getInstance();
            WebDriver driver = (WebDriver) testClass.getClass()
                .getMethod("getDriver")
                .invoke(testClass);

            ScreenshotUtil.captureScreenshot(driver, result.getName());
        } catch (Exception e) {
            System.out.println("❌ Failed to capture screenshot in listener: " + e.getMessage());
        }
    }
}
