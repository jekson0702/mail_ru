package mail;

import SingletonWebDriver.SingletonWebDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;

public class LoginPageTest {
    private WebDriver driver;
    private LoginPage loginPage;


    @BeforeMethod
    public void createDriver() {
        driver = SingletonWebDriver.getDriver();
        loginPage = new LoginPage();
        loginPage.loadMainPage();
    }

    @Test
    public void authorizationWithRigthParameters() {
        loginPage.loginAsCorrectUser();
        Assert.assertTrue(loginPage.logoutLinkPresents());
    }

    @Test
    public void authorizationWithWrongLogin() {
        loginPage.authorizationWithWrongLogin();
        Assert.assertTrue(loginPage.wrongLoginErrorIsPresent());
    }

    @Test
    public void authorizationWithWrongPassword() {
        loginPage.authorizationWithWrongPassword();
        Assert.assertTrue(loginPage.wrongPasswordErrorIsPresent());
    }


    @AfterMethod
    public void driverQuit() {
        SingletonWebDriver.quitDriver();
    }
}