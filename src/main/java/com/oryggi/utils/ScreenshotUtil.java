package com.oryggi.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    public static void captureScreenshot(WebDriver driver, String testName) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String fileName = testName + "_" + timestamp + ".png";
            File destFile = new File("screenshots/" + fileName);
            destFile.getParentFile().mkdirs();

            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            Files.copy(srcFile.toPath(), destFile.toPath());

            System.out.println("✅ Screenshot saved at: " + destFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("❌ Screenshot failed: " + e.getMessage());
        }
    }
}
