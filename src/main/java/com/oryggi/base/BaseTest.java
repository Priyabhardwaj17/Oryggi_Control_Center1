package com.oryggi.base;

import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;




public class BaseTest {

	protected  WebDriver driver;
	 protected WebDriverWait wait;
	
	@BeforeMethod
	public void setup() throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver =  new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://localhost/OryggiManagerWeb/login");
		 // Handle SSL Privacy Error Page
		byPassSSLError();
	}
	
	public void byPassSSLError() throws InterruptedException {
		if(driver.getTitle().contains("Privacy error")) {
			try {
				//click advanced Option
				driver.findElement(By.id("details-button")).click();
				Thread.sleep(1000);	
				
				//click to proceed
				driver.findElement(By.id("proceed-link")).click();
				Thread.sleep(1000);
				}catch(Exception e){
					System.out.println("SSL Warning not found or already bypassed.");
			}
			
		}
	}
	
	 // ✅ Added this method to allow ScreenshotUtil to access WebDriver
    public WebDriver getDriver() {
        return driver;
    }
    
	@AfterMethod
	public void tearDown() {
		if(driver != null) {
			//driver.quit();
		}
	}
	
	

}
