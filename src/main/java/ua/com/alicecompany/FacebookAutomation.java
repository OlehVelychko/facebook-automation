package ua.com.alicecompany;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

import java.util.Properties;

public class FacebookAutomation {
    private static final Logger logger = LoggerFactory.getLogger(FacebookAutomation.class);

    public static void main(String[] args) {
        logger.info("Starting the script...");
        String configFilePath = "src/main/resources/config.properties";
        Properties properties = new Properties();

        // Load configuration
        try {
            properties.load(Files.newInputStream(Paths.get(configFilePath)));
        } catch (IOException e) {
            logger.error("Failed to load configuration file: {}", e.getMessage());
            return;
        }

        String email = properties.getProperty("facebook.email");
        String password = properties.getProperty("facebook.password");

        // Specify the path to ChromeDriver
        System.setProperty("webdriver.chrome.driver", "/Users/narsil/Documents/java_dev/learning/tools_and_utilities/chromedriver-mac-arm64/chromedriver");

        // Initialize the browser
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://www.facebook.com/");

            WebElement emailField = driver.findElement(By.id("email"));
            emailField.sendKeys(email);

            WebElement passwordField = driver.findElement(By.id("pass"));
            passwordField.sendKeys(password);

            WebElement loginButton = driver.findElement(By.name("login"));
            loginButton.click();

            // Check for potential reCAPTCHA
            try {
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
                if (wait.until(ExpectedConditions.presenceOfElementLocated(By.className("g-recaptcha"))) != null) {
                    logger.info("reCAPTCHA detected. Please solve it manually.");
                    // Wait for user to solve reCAPTCHA
                    Thread.sleep(60000); // Adjust the time as needed
                }
            } catch (Exception e) {
                logger.info("No reCAPTCHA detected, proceeding.");
            }

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until(ExpectedConditions.urlContains("facebook.com"));

            logger.info("Login successful. Current URL: " + driver.getCurrentUrl());

            // Navigate to the profile page
            driver.get("https://www.facebook.com/me/");

            /* Uncomment this for debugging
            logger.info("Profile page HTML: {}", driver.getPageSource());
            */

            // Use JavascriptExecutor to locate the SVG element
            JavascriptExecutor js = (JavascriptExecutor) driver;
            WebElement profilePhoto = (WebElement) js.executeScript(
                    "return document.evaluate(" +
                            "  \"//div[contains(@aria-label, 'Действия с фото профиля')]//*[local-name()='image' and @xlink:href]\", " +
                            "  document, " +
                            "  function(prefix) { return prefix === 'xlink' ? 'http://www.w3.org/1999/xlink' : null; }, " +
                            "  XPathResult.FIRST_ORDERED_NODE_TYPE, " +
                            "  null" +
                            ").singleNodeValue;"
            );

            if (profilePhoto == null) {
                throw new RuntimeException("Profile photo element not found.");
            }

            // Extract the photo URL
            String photoUrl = profilePhoto.getAttribute("xlink:href");
            logger.info("Profile photo URL: {}", photoUrl);

            // Download the photo
            downloadImage(photoUrl, "profile_photo.jpg");
            logger.info("Profile photo downloaded successfully.");

        } catch (Exception e) {
            logger.error("Error: {}", e.getMessage());
        } finally {
            driver.quit();
        }
    }

    // Method to download an image from a URL
    private static void downloadImage(String imageUrl, String destinationFile) {
        try (InputStream in = new URL(imageUrl).openStream();
             FileOutputStream out = new FileOutputStream(destinationFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            logger.error("Error downloading image: {}", e.getMessage());
        }
    }
}