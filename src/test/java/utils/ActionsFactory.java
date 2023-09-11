package utils;

import core.App;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;

public class ActionsFactory {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ActionsFactory(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofMillis(App.ELEMENT_TIMEOUT));
    }

    public void waitElementUntil(WebElement webElement, WaitType type) {
        try {
            switch (type) {
                case VISIBLE:
                    wait.until(ExpectedConditions.visibilityOf(webElement));
                    break;
                case CLICKABLE:
                    wait.until(ExpectedConditions.elementToBeClickable(webElement));
                    break;
            }
        } catch (TimeoutException e) {
            LoggerLoad.warn("WARNING: Timeout waiting for element to become " + type);
            e.printStackTrace();
        }
    }

    @Step("📸 {0} - 📸 Full page screenshot")
    public void embedFullPageScreenshot(String description) {
        Allure.addAttachment("📸 " + description + " - 📸 Full page screenshot", new ByteArrayInputStream(((TakesScreenshot) this.driver).getScreenshotAs(OutputType.BYTES)));
    }

    @Step("🐾 \"{0}\" Element is clicked")
    public void click(WebElement webElement) {
        waitElementUntil(webElement, WaitType.CLICKABLE);
        webElement.click();
        LoggerLoad.info(webElement + " Element is clicked");
    }

    @Step("🐾 Getting \"{0}\" element text")
    public String getText(WebElement webElement) {
        waitElementUntil(webElement, WaitType.VISIBLE);
        LoggerLoad.info("Getting " + webElement + " element text");
        return webElement.getText();
    }

    @Step("🐾 Scrapping content from element \"{0}\"")
    public String[] scrapElement(WebElement webElement, String savePath) {
        waitElementUntil(webElement, WaitType.VISIBLE);
        String scrapedText = webElement.getText();
        String browserName = driver.getClass().getSimpleName();
        String saveBaseFolderPath = "target/specs";
        String filePath = saveBaseFolderPath + "/" + savePath + "/" + browserName + "-scriptedText.txt";

        if (scrapedText != null && !scrapedText.isEmpty()) {
            try {
                Files.createDirectories(Paths.get(saveBaseFolderPath, savePath));
                Files.write(Paths.get(filePath), scrapedText.getBytes());
                LoggerLoad.info("Scraping content from element " + webElement + " and saving to " + filePath);
            } catch (IOException e) {
                LoggerLoad.error("FAILED: Error saving scraped text to file: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            LoggerLoad.info("FAILED: Scraped text is empty or null, not saving to file.");
        }
        return new String[] {filePath, scrapedText};
    }

    @Step("🧪 Verify if scraped text was successfully saved to file: \"{0}\"")
    public void verifyScrapedTextSaved(String filePath, String scrapedText) {
        try {
            boolean fileExists = Files.exists(Paths.get(filePath));

            if (fileExists) {
                Allure.addAttachment("✅ Scraped text was successfully saved to file", scrapedText);
                embedFullPageScreenshot("✅ Scraped text was successfully saved to file");
                LoggerLoad.info("PASSED: Scraped text was successfully saved to file.");
            } else {
                Allure.addAttachment("💥 Failed to save scraped text to file", "💥 Failed to save scraped text to file");
                LoggerLoad.error("FAILED: Failed to save scraped text to file.");
            }

            Assert.assertTrue(fileExists);
        } catch (Exception e) {
            Allure.addAttachment("💥 Error verifying scraped text saved status: " + e.getMessage(), "💥 Error verifying scraped text saved status: " + e.getMessage());
            LoggerLoad.error("FAILED: Error verifying scraped text saved status: " + e.getMessage());
            Assert.fail("Error verifying scraped text saved status: " + e.getMessage());
        }
    }
}
