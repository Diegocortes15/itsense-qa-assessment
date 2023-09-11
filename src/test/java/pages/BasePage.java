package pages;

import utils.ActionsFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public abstract class BasePage {
    protected WebDriver driver;
    protected ActionsFactory ActionsFactory;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        this.ActionsFactory = new ActionsFactory(this.driver);
    }
}
