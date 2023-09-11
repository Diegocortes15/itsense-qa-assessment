package specs;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import pages.HomePage;

@Feature("Login")
public class IS_0001 extends BaseTest {

    private final String storyParent = "is-0001";

    @Test
    @Story(storyParent)
    @Description("Validate the 'Header' tag content")
    @Severity(SeverityLevel.NORMAL)
    public void is_0002() {
        String testCase = "is-0002";
        HomePage homePage = new HomePage(driver);
        homePage.goTo();
        String[] scrapedFile = homePage.scrapHeaderTag(storyParent + "/" + testCase);
        homePage.verifyHeaderScraped(scrapedFile);
    }
}
