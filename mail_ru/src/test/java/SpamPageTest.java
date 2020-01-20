import SingletonWebDriver.SingletonWebDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.SpamPage;

public class SpamPageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private SpamPage spamPage;

    @BeforeClass
    public void beforeClass() {
        driver = SingletonWebDriver.getDriver();
        loginPage = new LoginPage();
        spamPage = new SpamPage();
    }

    @Test
    public void returnFromSpamTest() {
        loginPage.loadMainPage();
        loginPage.loginAsCorrectUser();
        spamPage.returnMessageFromSpam();
        Assert.assertTrue(spamPage.returnFromSpamAlertIsPresents());
    }

    @AfterClass
    public void afterClass() {
        SingletonWebDriver.quitDriver();
    }
}