package com.oryggi.tests;

import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.oryggi.pages.LoginPage;
import com.oryggi.base.BaseTest;
import com.oryggi.pages.DashboardPage;

@Listeners(com.oryggi.listeners.TestListener.class)
public class DashboardTest extends BaseTest {

    @Test
    public void testLogoutFunctionality() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserNameOrEmail("Admin");
        loginPage.enterPassword("Oryggi@123");
        loginPage.clickLogin();

        // Navigate to Dashboard & Perform Logout
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.clickProfileIcon();
        dashboardPage.clickLogout();

        // ✅ Fix: Declare and use WebDriverWait
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.urlContains("login"));

        // Validate redirection to login page
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("login"), "Logout failed! User is not redirected to Login Page.");
    }
}
