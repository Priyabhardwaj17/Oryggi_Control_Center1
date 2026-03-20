package com.oryggi.utils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.oryggi.pages.LoginPage;

import java.util.HashMap;
import java.util.Map;

public class ResponsiveUtil {

    private static final Map<String, Dimension> resolutions = new HashMap<>();

    static {
    	  //  Mobile Devices
        resolutions.put("iPhone SE", new Dimension(375, 667));
        resolutions.put("iPhone 12", new Dimension(390, 844));
        resolutions.put("Pixel 5", new Dimension(393, 851));
        resolutions.put("Galaxy S20", new Dimension(412, 915));
        resolutions.put("OnePlus 9 Pro", new Dimension(412, 915));

        //  Tablets
        resolutions.put("iPad", new Dimension(768, 1024));
        resolutions.put("Galaxy Tab", new Dimension(800, 1280));

        //  Laptops & Desktops
        resolutions.put("Laptop", new Dimension(1366, 768));
        resolutions.put("MacBook Pro 13", new Dimension(1440, 900));
        resolutions.put("Desktop", new Dimension(1920, 1080));
    
    }

    public static void checkResponsivenessWithLogin(WebDriver driver, String pageName, String url, String username, String password) {
        for (Map.Entry<String, Dimension> entry : resolutions.entrySet()) {
            String device = entry.getKey();
            Dimension dimension = entry.getValue();

            try {
                driver.manage().window().setSize(dimension);
                driver.get(url);

                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@data-placeholder='Please enter UId/Email here...']")));

                LoginPage loginPage = new LoginPage(driver);
                loginPage.enterUserNameOrEmail(username);
                loginPage.enterPassword(password);
                loginPage.clickLogin();

                wait.until(ExpectedConditions.urlContains("dashboard"));

                // Wait for Angular to stabilize (optional)
                loginPage.waitForAngularRequestsToFinish();

                // Take Screenshot
                TakesScreenshot ts = (TakesScreenshot) driver;
                File srcFile = ts.getScreenshotAs(OutputType.FILE);
                File destFile = new File("screenshots/" + pageName + "_" + device + ".png");

                // Ensure screenshot folder exists
                destFile.getParentFile().mkdirs();
                org.openqa.selenium.io.FileHandler.copy(srcFile, destFile);

                System.out.println("✅ Screenshot captured for: " + device + " at " + destFile.getAbsolutePath());

            } catch (IOException e) {
                System.err.println("❌ Failed to save screenshot for " + device + ": " + e.getMessage());
            } catch (Exception e) {
                System.err.println("❌ Error occurred for " + device + " resolution: " + e.getMessage());
            }
        }
    }
}
