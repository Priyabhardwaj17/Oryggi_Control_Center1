package com.oryggi.tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.oryggi.base.BaseTest;
import com.oryggi.pages.LoginPage;
import com.oryggi.utils.ResponsiveUtil;

@Listeners(com.oryggi.listeners.TestListener.class)
public class LoginTest extends BaseTest {

    @Test
    public void verifyLoginPageTitle() {
        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.titleContains("Oryggi Control Center"));
        String expectedTitle = "Oryggi Control Center";
        String actualTitle = getDriver().getTitle();
        System.out.println("Actual Title Retrieved: '" + actualTitle + "'");
        Assert.assertEquals(actualTitle, expectedTitle, "Title mismatch!");
    }

    @Test
    public void testLoginWithUsername() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterUserNameOrEmail("Admin");
        loginPage.enterPassword("Oryggi@123");
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));

        String actualURL = getDriver().getCurrentUrl();
        String expectedURL = "https://localhost/OryggiManagerWeb/dashboards/employee-dashboard";
        Assert.assertEquals(actualURL, expectedURL, "Login failed! User is not redirected to Dashboard.");
    }

    @Test
    public void testLoginWithEmail() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterUserNameOrEmail("oryggiserver@gmail.com");
        loginPage.enterPassword("Oryggi@123");
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));

        String actualURL = getDriver().getCurrentUrl();
        String expectedURL = "https://localhost/OryggiManagerWeb/dashboards/employee-dashboard";
        Assert.assertEquals(actualURL, expectedURL, "Login failed! User is not redirected to Dashboard.");
    }

    @Test
    public void testLoginWithInvalidCredentials() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterUserNameOrEmail("InvalidUser");
        loginPage.enterPassword("WrongPassword");
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement snackBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//simple-snack-bar/span")));
        String actualMessage1 = snackBar.getText();
        String expectedMessage = "Invalid User Id or Password.";
        Assert.assertTrue(actualMessage1.contains(expectedMessage), "Error message mismatch! Expected: " + expectedMessage + ", Found: " + actualMessage1);
    }

    @Test
    public void testEmptyFieldsValidation() throws InterruptedException {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement snackBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//simple-snack-bar/span")));

        String actualMessage = snackBar.getText();
        String expectedMessage = "User Id cannot be Empty.";
        Assert.assertEquals(actualMessage, expectedMessage, "Validation message mismatch!");
    }

    @Test
    public void testLoginWithEmptyPassword() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterUserNameOrEmail("Admin");
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement snackBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//simple-snack-bar/span")));

        String actualMessage = snackBar.getText();
        String expectedMessage = "Password cannot be Empty.";
        Assert.assertEquals(actualMessage, expectedMessage, "Validation message mismatch!");
    }

    @Test
    public void testLoginWithEmptyUsername() {
        LoginPage loginPage = new LoginPage(getDriver());
        loginPage.enterPassword("Oryggi@1234");
        loginPage.clickLogin();

        WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        WebElement snackBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//simple-snack-bar/span")));

        String actualMessage = snackBar.getText();
        String expectedMessage = "User Id cannot be Empty.";
        Assert.assertEquals(actualMessage, expectedMessage, "Validation message mismatch!");
    }

    @Test
    public void testRememberMeFunctionality() {
        LoginPage loginPage = new LoginPage(getDriver());

        System.out.println("Entering username...");
        loginPage.enterUserNameOrEmail("Admin");

        System.out.println("Entering password...");
        loginPage.enterPassword("Oryggi@1234");

        System.out.println("Clicking Remember Me checkbox...");
        loginPage.selectRememberMe();

        System.out.println("Clicking Login button...");
        loginPage.clickLogin();

        System.out.println("Refreshing page...");
        getDriver().navigate().refresh();

        String rememberedUsername = loginPage.getUsernameFieldText();
        System.out.println("Remembered Username: " + rememberedUsername);

        Assert.assertEquals(rememberedUsername, "Admin", "Remember Me functionality failed!");
    }

    @Test
    public void testPasswordMasking() {
        LoginPage loginPage = new LoginPage(getDriver());

        WebElement passwordField = loginPage.getPasswordField();
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        String fieldType = (String) js.executeScript("return arguments[0].type;", passwordField);

        Assert.assertEquals(fieldType, "password", "Password is NOT masked!");
    }
    
    @Test (priority = 1)
    public void testLoginPageResponsiveness() {
        ResponsiveUtil.checkResponsivenessWithLogin(
            getDriver(),
            "LoginPage",
            "https://localhost/OryggiManagerWeb/login",
            "Admin",
            "Oryggi@123"
        );
    }


}
