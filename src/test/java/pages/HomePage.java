package pages;

import core.App;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    private final String url = App.BASE_URL;

    @FindBy(xpath = "(//*/header)[1]")
    private WebElement header;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("⏩ Go to " + url)
    public void goTo() {
        driver.get(this.url);
    }

    @Step("⏩ Scrapping Header Tag")
    public String[] scrapHeaderTag(String savePath) {
        return ActionsFactory.scrapElement(header, savePath);
    }

    @Step("🧪 Verify Header Scraped")
    public void verifyHeaderScraped(String[] scrapedFile){
        ActionsFactory.verifyScrapedTextSaved(scrapedFile[0], scrapedFile[1]);
    }
/*
    @Step("🧪 Verify validation message: \"{0}\"")
    public void verifyValidationMessage(String validationMessage) {
        ActionsFactory.verifyText(errorMessage, validationMessage);
    }

    @Step("🧪 Verify URL:" + url)
    public void verifyLoginURL() {
        ActionsFactory.verifyURLToBe(this.url);
    }*/
}
