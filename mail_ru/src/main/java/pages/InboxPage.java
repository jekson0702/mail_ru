package pages;

import SingletonWebDriver.SingletonWebDriver;
import Waits.Waits;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InboxPage {
    Waits waits = new Waits();
    private Logger logger = Logger.getLogger(InboxPage.class);

    @FindBy(xpath = "(//div[contains(@class,'ll-av')])[1]")
    private WebElement firstMessageCheckbox;

    @FindBy(xpath = "//span[contains(@title,\"Спам\")]")
    private WebElement toSpamButton;

    @FindBy(xpath = "//*[contains(text(),'Перемещено в спам')]")
    private WebElement spamAlert;

    @FindBy(xpath = "(//button[contains(@title,\"Пометить флажком\")])[1]")
    private WebElement flag;

    @FindBy(xpath = "//button[contains(@title,\"Пометить флажком\")]")
    private List<WebElement> flags;

    @FindBy(xpath = "//button[contains(@title,\"Снять флажок\")]")
    private WebElement deflag;

    @FindBy(xpath = "//button[@data-title=\"Снять флажок\" or @title=\"Снять флажок\"]")
    private List<WebElement> flagedList;

    @FindBy(xpath = "//a[@href=\"/inbox/\"]")
    private WebElement inbox;

    public InboxPage() {
        PageFactory.initElements(SingletonWebDriver.getDriver(), this);
    }

    public void chooseMessage() {
        waits.expectClickableAndClick(firstMessageCheckbox);
        logger.info("first message is chosen");
    }

    public void clickToSpam() {
        waits.expectClickableAndClick(toSpamButton);
        logger.info("click to spam button");
    }

    public void markWithFlag(int numberOfMessages) {
        do {
            waits.expectClickableAndClick(flag);
            waits.expectVisibilityOfAllElements(flagedList);
        }
        while (flagedList.size() < numberOfMessages);
        logger.info("messages are marked with flags");
    }

    public void uncheckWithFlag() {
        do {
            waits.expectVisibilityOfAllElements(flagedList);
            waits.expectClickableAndClick(deflag);
            waits.expectVisibilityOfAllElements(flags);
        }
        while (flagedList.size() > 0);
        logger.info("messages are unchecked with flags");
    }

    public boolean toSpamAlertIsPresent() {
        return waits.expectVisibilityAndCheck(spamAlert);
    }

    public boolean flagsArePresent(int numberOfFlags) {
        waits.expectVisibilityOfAllElements(flagedList);
        return (flagedList.size() >= numberOfFlags);
    }

    public boolean flagsAreNotPresent() {
        waits.expectVisibilityOfAllElements(flags);
        return flagedList.isEmpty();
    }
}