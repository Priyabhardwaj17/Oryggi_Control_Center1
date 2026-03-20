package com.oryggi.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {
	WebDriver driver;  //driver initialization
	WebDriverWait Wait;    //explicit wait to handle syncronization
	
	//Locators
	By emailField = By.xpath("//input[@id='exampleEmail']");
	By continueButton  = By.xpath("//button[normalize-space()='Continue']");
	By otpPopupOkButton = By.xpath("//button[normalize-space()='OK']"); //click otp button
	By otpField = By.xpath("//input[@id='OTPNumber']");
	By NewPasswordField = By.xpath("//input[@id='newPassword']");
	By reEnterPasswordField = By.xpath("//input[@id='reEnterNewPassword']");
	By CreatePasswordButton = By.xpath("//button[normalize-space()='Create Password']");
	By passwordUpdateMessage = By.xpath("//div[@id='swal2-content']");
	By okButton = By.xpath("//button[normalize-space()='OK']");
	
	
	//initialize
	public ForgotPasswordPage(WebDriver driver) {
		this.driver = driver;
		this.Wait = new WebDriverWait(driver, Duration.ofSeconds(15));	
	}
	
	//Actions
	public void enterEmail(String email) {
		WebElement emailElement = Wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
		emailElement.clear();
		emailElement.sendKeys(email);
	}
	
	public void clickContinue() {
		Wait.until(ExpectedConditions.visibilityOfElementLocated(continueButton)).click();
	}
	
	public void handleOtpPopup() {
        WebElement okButton = Wait.until(ExpectedConditions.elementToBeClickable(otpPopupOkButton));
        okButton.click();
    }
	
	public void enterOTP(String otp) {
		WebElement otpElement = Wait.until(ExpectedConditions.visibilityOfElementLocated(otpField));
		otpElement.clear();
		otpElement.sendKeys(otp);
	}
	
	public void enterNewPassword(String newPassword) {
		WebElement passwordElement = Wait.until(ExpectedConditions.visibilityOfElementLocated(NewPasswordField));
		passwordElement.clear();
		passwordElement.sendKeys(newPassword);
	}
	
	public void reEnterNewPassword (String confirmPassword) {
		WebElement confirmNewPassword = Wait.until(ExpectedConditions.visibilityOfElementLocated(reEnterPasswordField));
		confirmNewPassword.clear();
		confirmNewPassword.sendKeys(confirmPassword);
	}
	
	public void clickCreatePassword () {
		Wait.until(ExpectedConditions.visibilityOfElementLocated(CreatePasswordButton)).click();
	}
	
	public Boolean isPasswordUpdatedMessageDisplayed() {
		return Wait .until(ExpectedConditions.visibilityOfElementLocated(passwordUpdateMessage)).isDisplayed();
	}
	
	public void clickOnPopUp() {
	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	    try {
	        // Check if a JavaScript alert is present
	        wait.until(ExpectedConditions.alertIsPresent());
	        driver.switchTo().alert().accept();
	        System.out.println("JavaScript alert dismissed.");
	    } catch (Exception e) {
	        System.out.println("No JS alert found, trying normal OK button click.");
	        
	        try {
	            // If not a JS alert, try clicking the OK button
	            WebElement okButtonElement = wait.until(ExpectedConditions.elementToBeClickable(okButton));
	            okButtonElement.click();
	            System.out.println("Clicked OK button successfully.");
	        } catch (Exception ex) {
	            System.out.println("Could not click OK button.");
	            ex.printStackTrace();
	        }
	    }
	}
	
	

}
