package specs;

import utils.LoggerLoad;
import core.WebDriverFactory;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public abstract class BaseTest {
    public WebDriver driver;

    @BeforeTest
    @Description("Browser start up")
    @Step("⏯ Browser start up - {0}")
    @Parameters("browser")
    public void setDriver(String browser) {
        LoggerLoad.info("Browser start up - " + browser);
        driver = new WebDriverFactory().createInstance(browser);
        driver.manage().window().maximize();
    }

    @AfterTest
    @Description("Browser tear down")
    @Step("🔚 Browser tear down")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        LoggerLoad.info("Browser tear down");
    }

    public WebDriver getDriver() {
        return driver;
    }
}

