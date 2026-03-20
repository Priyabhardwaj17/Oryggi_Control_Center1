package com.oryggi.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.oryggi.pages.LoginPage;
import java.time.Duration;

public class ForgotPasswordClickTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Navigate to the login page
        driver.get("https://localhost/OryggiManagerWeb/login");
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @Test
    public void testForgotPasswordClick() {
        LoginPage loginPage = new LoginPage(driver);
        // Click the Forgot Password button
        loginPage.clickForgotPassword();

        // If the link opens in a new tab, switch to it
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // Wait until the URL contains "pages/forgot-password"
        wait.until(ExpectedConditions.urlContains("https://localhost/OryggiManagerWeb/pages/forgot-password"));

        // Validate the URL
        String expectedURLFragment = "https://localhost/OryggiManagerWeb/pages/forgot-password";
        String actualURL = driver.getCurrentUrl();
        Assert.assertTrue(actualURL.contains(expectedURLFragment),
                "Forgot Password page did not open. Expected URL to contain: " + expectedURLFragment + ", but got: " + actualURL);
    }
}
