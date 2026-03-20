package com.oryggi.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By userNameOrEmailField = By.xpath("//input[@data-placeholder='Please enter UId/Email here...']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By loginButton = By.xpath("//span[text()='Login']");
    private By forgotPasswordButton = By.xpath("//span[normalize-space()='Forgot Password ?']"); 
    private By rememberMeCheckbox = By.xpath("//span[@class='mat-checkbox-inner-container']");


    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15)); // Explicit wait for stability
    }

    // Actions
    public void enterUserNameOrEmail(String userInput) {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(userNameOrEmailField));
        usernameField.sendKeys(userInput);
    }

    public void enterPassword(String password) {
        WebElement passwordFieldElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordFieldElement.sendKeys(password);
    }

    public void clickLogin() {
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();
    }

    public void waitForAngularRequestsToFinish() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        wait.until(driver -> (Boolean) jsExecutor.executeScript("return window.getAllAngularTestabilities().every(t => t.isStable());"));
    }

    // Click Forgot Password
    public WebElement getForgotPasswordElement() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            // ✅ Fix: Check if the element is in an iframe
            int iframesCount = driver.findElements(By.tagName("iframe")).size();
            if (iframesCount > 0) {
                driver.switchTo().frame(0); // Switch to the first iframe
            }

            WebElement forgotPasswordElement = wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordButton));

            // ✅ Fix: Switch back to main page after finding element
            driver.switchTo().defaultContent();
            
            return forgotPasswordElement;
        } catch (Exception e) {
            throw new RuntimeException("Failed to locate Forgot Password element: " + e.getMessage());
        }
    }

    public void clickForgotPassword() {
        try {
            WebElement forgotPasswordLink = getForgotPasswordElement();

            // ✅ Fix: Scroll to make sure it's in view
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", forgotPasswordLink);

            // ✅ Fix: Use JavaScript click for better compatibility
            js.executeScript("arguments[0].click();", forgotPasswordLink);

            System.out.println("Forgot Password clicked successfully.");
        } catch (Exception e) {
            throw new RuntimeException("Failed to click Forgot Password link: " + e.getMessage());
        }
    }
    
    public void selectRememberMe() {
        WebElement rememberMe = wait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));

        // Scroll into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", rememberMe);

        // Click the checkbox
        rememberMe.click();

        // ✅ Verify checkbox state using `isSelected()` (not deprecated)
        WebElement inputElement = rememberMe.findElement(By.tagName("input"));
        boolean isChecked = inputElement.isSelected();
        System.out.println("Remember Me checkbox selected: " + isChecked);

        if (!isChecked) {
            throw new RuntimeException("Remember Me checkbox click failed!");
        }
    }
    
    public String getUsernameFieldText() {
        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(userNameOrEmailField));
        return usernameField.getDomProperty("value"); // Alternative to getAttribute("value")
    }
    
    public WebElement getPasswordField() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
    }


    
    


}
