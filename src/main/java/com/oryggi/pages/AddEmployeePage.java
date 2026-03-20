package com.oryggi.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AddEmployeePage {
    WebDriver driver;
    WebDriverWait wait;

    // Constructor
    public AddEmployeePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Locator for "Add Employee" button
    private By addEmployeeButton = By.xpath("//button[.//mat-icon[text()='add']]");
    private By employeeIdField = By.xpath("//input[@id='mat-input-4']");
    private By searchIcon = By.xpath("//mat-icon[normalize-space()='person_search']"); 
    



    // Click "Add Employee" Button
    public void clickAddEmployee() {
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addEmployeeButton));

        // Click using JavaScript if normal click fails
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
        System.out.println("✅ Successfully clicked 'Add Employee' button via JS.");
    }
    
 // Method to enter Employee ID
    public void enterEmployeeId(String empId) {
        WebElement empIdInput = wait.until(ExpectedConditions.visibilityOfElementLocated(employeeIdField));
        empIdInput.clear();
        empIdInput.sendKeys(empId);
        System.out.println("✅ Entered Employee ID: " + empId);
    }

    // Method to click the Search icon
    public void clickSearchIcon() {
        WebElement searchBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(searchIcon));
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();
        System.out.println("✅ Clicked on Search icon.");
    }
}




