package pages;

import SingletonWebDriver.SingletonWebDriver;
import Waits.Waits;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SpamPage {
    private Waits waits = new Waits();
    private Logger logger = Logger.getLogger(SpamPage.class);
    private WebDriver driver = SingletonWebDriver.getDriver();
    private JavascriptExecutor executor = (JavascriptExecutor) driver;

    @FindBy(xpath = "(//div[@class=\"ll-av__container\"])[1]")
    private WebElement firstMessageCheckbox;

    @FindBy(xpath = "//a[@href=\"/spam/\"]")
    private WebElement spamButton;

    @FindBy(xpath = "//span[@data-title-shortcut=\"Shift+J\"]")
    private WebElement notSpamButton;

    @FindBy(xpath = "//*[@id=\"app-canvas\"]" +
            "/div/div[1]/div[1]/div/div[2]/div[2]/div/div/div/div/div/div[1]/div/div/div/div/div[2]/a")
    private WebElement clearFolderButton;

    @FindBy(xpath = "//*[contains(text(),'Перемещено в папку')]")
    private WebElement toAnotherFolderAlert;

    public SpamPage() {
        PageFactory.initElements(SingletonWebDriver.getDriver(), this);
    }

    public void returnMessageFromSpam() {
        waits.expectClickableAndClick(spamButton);
        logger.info("click spam button");
        waits.expectVisibility(clearFolderButton);
        waits.expectClickableAndClick(firstMessageCheckbox);
        logger.info("first message is chosen");
        waits.expectClickableAndClick(notSpamButton);
        logger.info("click NotSpam button");
    }

    public boolean returnFromSpamAlertIsPresents() {
        return waits.expectVisibilityAndCheck(toAnotherFolderAlert);
    }
}