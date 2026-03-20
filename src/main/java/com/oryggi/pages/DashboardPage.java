package com.oryggi.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DashboardPage {
    private WebDriver driver;
    private WebDriverWait wait;

    // Locators
    private By profileIcon = By.xpath("//button[@class='dropdown-toggle btn btn-link p-0 mr-2']");
    private By logoutButton = By.xpath("//button[@class='btn-pill btn-shadow btn-shine btn btn-focus']");

    // Constructor
    public DashboardPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    // Click Profile Icon (for Logout)
    public void clickProfileIcon() {
        WebElement profile = wait.until(ExpectedConditions.visibilityOfElementLocated(profileIcon));
        
        try {
            profile.click(); // Normal click
        } catch (Exception e) {
            // Fallback: Click using JavaScript if intercepted
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", profile);
        }
    }

    // Click Logout Button
    public void clickLogout() {
        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(logoutButton));
        
        try {
            logout.click(); // Normal click
        } catch (Exception e) {
            // Scroll to button and retry
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", logout);
            js.executeScript("arguments[0].click();", logout);
        }
    }
}
