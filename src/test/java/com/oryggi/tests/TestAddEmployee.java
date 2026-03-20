package com.oryggi.tests;

import java.time.Duration;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.oryggi.base.BaseTest;
import com.oryggi.pages.LoginPage;
import com.oryggi.pages.AddEmployeePage;

public class TestAddEmployee extends BaseTest {

    @BeforeMethod
    public void setUp() {
        // Step 1: Login only once before each test
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterUserNameOrEmail("Admin");
        loginPage.enterPassword("Oryggi@1234");
        loginPage.clickLogin();

        // Step 2: Wait for Dashboard
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("dashboard"));

        System.out.println("✅ Successfully logged in and navigated to the dashboard.");
    }

    @Test (priority = 1)
    public void testAddEmployeeButton() {
        // Step 3: Click on "Add Employee"
        AddEmployeePage addEmpPage = new AddEmployeePage(driver);
        addEmpPage.clickAddEmployee();

        // Step 4: Verify the Add Employee page URL
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String expectedURL = "https://localhost/OryggiManagerWeb/dashboards/enroll-visitor?Operation=EnrollE";
        wait.until(ExpectedConditions.urlToBe(expectedURL));

        String actualURL = driver.getCurrentUrl();
        Assert.assertEquals(actualURL, expectedURL, "Navigation to Add Employee page failed!");

        System.out.println("✅ Successfully navigated to Add Employee page.");
    }

    @Test (priority = 2)
    public void testSearchEmployee() {
        // Step 1: Click on "Add Employee"
        AddEmployeePage addEmpPage = new AddEmployeePage(driver);
        addEmpPage.clickAddEmployee();

        // Step 2: Wait for Add Employee page to load
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("Operation=EnrollE"));

        // Step 3: Enter Employee ID
        addEmpPage.enterEmployeeId("EMP123");

        // Step 4: Wait for Search icon to appear & Click it
        addEmpPage.clickSearchIcon();
    }
    
    
    
}
