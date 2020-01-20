import SingletonWebDriver.SingletonWebDriver;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import pages.LoginPage;
import pages.SendMessagePage;

public class SendMessageTest {
    private SendMessagePage sendMessagePage;
    private LoginPage loginPage;
    private WebDriver driver;
    private EyesRunner runner;
    private Eyes eyes;
    private String apiKey = Configuration.getApiKey();

    @BeforeClass
    public void createDriver() {
        driver = SingletonWebDriver.getDriver();
        loginPage = new LoginPage();
        sendMessagePage = new SendMessagePage();
        runner = new ClassicRunner();
        eyes = new Eyes(runner);
        eyes.setApiKey(apiKey);
        eyes.setMatchLevel(MatchLevel.CONTENT);
    }

    @Test
    public void sendMessageWithApplitools() {
        eyes.open(driver, "MailRu", "SendingMessageTest",
                new RectangleSize(1400, 600));
        loginPage.loadMainPage();
        eyes.checkWindow("MainPage");
        loginPage.loginAsCorrectUser();
        sendMessagePage.clickWriteMessageButton();
        sendMessagePage.enterAddress();
        sendMessagePage.enterMessageText();
        eyes.checkWindow("ScreenBeforeSending");
        sendMessagePage.sendMessage();
        eyes.checkWindow("SendMessageAlert");
        eyes.closeAsync();
        Assert.assertTrue(sendMessagePage.visualTestIsPassed(runner));
    }

    @AfterClass
    public void afterClass() {
        SingletonWebDriver.quitDriver();
        eyes.abortIfNotClosed();
    }
}