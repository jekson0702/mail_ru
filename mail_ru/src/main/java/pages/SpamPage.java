package pages;

import SingletonWebDriver.SingletonWebDriver;
import Waits.Waits;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SpamPage {
    private WebDriver driver = SingletonWebDriver.getDriver();
    private Waits waits = new Waits();

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
        PageFactory.initElements(driver, this);
    }

    public void returnMessageFromSpam() {
        waits.expectClickableAndClick(spamButton);
        waits.expectVisibility(clearFolderButton);
        waits.expectClickableAndClick(firstMessageCheckbox);
        waits.expectClickableAndClick(notSpamButton);
    }

    public boolean returnFromSpamAlertIsPresents() {
        return waits.expectVisibilityAndCheck(toAnotherFolderAlert);
    }
}
