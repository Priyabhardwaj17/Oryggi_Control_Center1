package com.oryggi.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.oryggi.base.BaseTest;
import com.oryggi.pages.ForgotPasswordPage;
import com.oryggi.pages.LoginPage;
import com.oryggi.utils.EmailUtils;
import java.time.Duration;

@Listeners(com.oryggi.listeners.TestListener.class)
public class ForgotPasswordTest extends BaseTest {
    
    @Test(priority = 1)
    public void testForgotPasswordNavigation() {
        LoginPage loginPage = new LoginPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Wait for Forgot Password link and click it
        wait.until(ExpectedConditions.elementToBeClickable(loginPage.getForgotPasswordElement())).click();

        // Switch to new tab if opened
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // Wait for URL to change to Forgot Password page
        wait.until(ExpectedConditions.urlToBe("https://localhost/OryggiManagerWeb/pages/forgot-password"));

        // Validate the URL
        Assert.assertEquals(driver.getCurrentUrl(), "https://localhost/OryggiManagerWeb/pages/forgot-password", "URL mismatch!");
    }

    @Test(priority = 2)
    public void testForgetPassword() {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        // Click "Forgot Password"
        LoginPage loginPage = new LoginPage(driver);
        loginPage.getForgotPasswordElement().click();

        // ✅ Switch to the new tab
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
        }

        // ✅ Wait for the email field to be visible before entering data
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='exampleEmail']")));

        // Enter email
        forgotPasswordPage.enterEmail("oryggiserver@gmail.com");
        forgotPasswordPage.clickContinue();

        // Handle OTP confirmation popup
        forgotPasswordPage.handleOtpPopup();

        // Fetch OTP from email
        String otp = EmailUtils.getLatestOtp();
        Assert.assertNotNull(otp, "OTP was not received!");

        forgotPasswordPage.enterOTP(otp);
        forgotPasswordPage.enterNewPassword("Oryggi@123");
        forgotPasswordPage.reEnterNewPassword("Oryggi@123");
        forgotPasswordPage.clickCreatePassword();

        // Click OK on success popup
        forgotPasswordPage.clickOnPopUp();

        // ✅ Wait for the login page to load
        wait.until(ExpectedConditions.urlToBe("https://localhost/OryggiManagerWeb/login"));

        // Validate the redirection
        Assert.assertEquals(driver.getCurrentUrl(), "https://localhost/OryggiManagerWeb/login", "Redirection to login page failed!");
    }

    
    
}

