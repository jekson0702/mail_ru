package pages;

import SingletonWebDriver.SingletonWebDriver;
import Waits.Waits;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InboxPage {
    WebDriver driver = SingletonWebDriver.getDriver();
    Waits waits = new Waits();

    @FindBy(xpath = "(//div[contains(@class,'ll-av')])[1]")
    private WebElement firstMessageCheckbox;

    @FindBy(xpath = "//span[@title=\"Спам\"]")
    private WebElement toSpamButton;

    @FindBy(xpath = "//*[contains(text(),'Перемещено в спам')]")
    private WebElement spamAlert;

    @FindBy(xpath = "(//button[@title=\"Пометить флажком\"])[1]")
    private WebElement flag;

    @FindBy(xpath = "//button[@title=\"Пометить флажком\"]")
    private List<WebElement> flags;

    @FindBy(xpath = "//button[@title=\"Снять флажок\"]")
    private WebElement deflag;

    @FindBy(xpath = "//button[@data-title=\"Снять флажок\" or @title=\"Снять флажок\"]")
    private List<WebElement> flagedList;

    @FindBy(xpath = "//a[@href=\"/inbox/\"]")
    private WebElement inbox;

    public InboxPage() {
        PageFactory.initElements(driver, this);
    }

    public void chooseMessage() {
        waits.expectClickableAndClick(firstMessageCheckbox);
    }

    public void clickToSpam() {
        waits.expectClickableAndClick(toSpamButton);
    }

    public void markWithFlag(int numberOfMessages) {
        do {
            waits.expectClickableAndClick(flag);
            waits.expectVisibilityOfAllElements(flagedList);
        }
        while (flagedList.size() < numberOfMessages);
    }

    public void uncheckkWithFlag() {
        do {
            waits.expectVisibilityOfAllElements(flagedList);
            waits.expectClickableAndClick(deflag);
            waits.expectVisibilityOfAllElements(flags);
        }
        while (flagedList.size() > 0);
    }

    public boolean toSpamAlertIsPresent() {
        return waits.expectVisibilityAndCheck(spamAlert);

    }

    public boolean flagsArePresent(int numberOfFlags) {
        waits.expectVisibilityOfAllElements(flagedList);
        return (flagedList.size() == numberOfFlags);
    }

    public boolean flagsAreNotPresent() {
        waits.expectVisibilityOfAllElements(flags);
        return flagedList.isEmpty();
    }
}