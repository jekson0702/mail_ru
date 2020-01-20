package pages;

import SingletonWebDriver.SingletonWebDriver;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class InboxSteps {
    private static final int NUMBER_OF_FLAGS = 3;
    private InboxPage inboxPage;
    private WebDriver driver;
    private LoginPage loginpage;
    private Logger logger = Logger.getLogger(InboxSteps.class);

    public InboxSteps() {
        loginpage = new LoginPage();
        driver = SingletonWebDriver.getDriver();
        inboxPage = new InboxPage();
    }

    @Given("^I am on InboxPage$")
    public void loadInboxPage() {
        loginpage.loadMainPage();
        loginpage.loginAsCorrectUser();
    }

    @When("^I choose first message$")
    public void chooseMessage() {
        inboxPage.chooseMessage();
    }

    @When("^I click 'to spam'$")
    public void clickToSpam() {
        inboxPage.clickToSpam();
    }

    @Then("^I see 'Moved to spam alert'$")
    public void movedToSpamAlertIsPresent() {
        Assert.assertTrue(inboxPage.toSpamAlertIsPresent());
    }

    @When("^I mark three messages with flags$")
    public void markMessagesWithFlags() {
        inboxPage.markWithFlag(NUMBER_OF_FLAGS);
    }

    @Then("^I see three marked messages$")
    public void markedMessagesArePresent() {
        Assert.assertTrue(inboxPage.flagsArePresent(NUMBER_OF_FLAGS));
    }

    @When("^i uncheck all Flags$")
    public void iUncheckAllFlags() {
        inboxPage.uncheckkWithFlag();
    }

    @Then("^I see no flags$")
    public void iSeeNoFlags() {
        Assert.assertTrue(inboxPage.flagsAreNotPresent());
    }

    @After()
    public void afterMessageToSpam() {
        SingletonWebDriver.quitDriver();
    }
//
//    @After("@markMessages")
//    public void afterMarkMessages() {
//        SingletonWebDriver.quitDriver();
//    }
//
//    @After("@uncheckMessages")
//    public void afterUncheckMessages() {
//        SingletonWebDriver.quitDriver();
//    }
}