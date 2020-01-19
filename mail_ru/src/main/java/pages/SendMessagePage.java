package pages;

import SingletonWebDriver.SingletonWebDriver;
import Waits.Waits;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.TestResultsSummary;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SendMessagePage {

    private WebDriver driver = SingletonWebDriver.getDriver();
    private Waits waits = new Waits();

    @FindBy(xpath = "//span[contains(text(),'Написать письмо')]")
    private WebElement writeMessageButton;

    @FindBy(xpath = "/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[2]/div/div" +
            "/div[1]/div/div[2]/div/div/label/div/div/input")
    private WebElement emailInput;

    @FindBy(xpath = "//span[@title='Отправить']")
    private WebElement sendMessageButton;

    @FindBy(xpath = "/html/body/div[9]/div/div/div[2]/div[2]/div/div/div[2]/span")
    private WebElement sendAlert;

    @FindBy(xpath = "/html/body/div[15]/div[2]/div/div[1]/div[2]/div[3]/div[5]" +
            "/div/div/div[2]/div[1]/div[1]")
    private WebElement messageTextField;

    public SendMessagePage() {
        PageFactory.initElements(driver, this);
    }

    public void enterAddress(String address) {
        waits.expectClickableAndClick(writeMessageButton);
        waits.expectClickable(emailInput);
        emailInput.sendKeys(address);
    }

    public void enterMessageText(String messageText) {
        messageTextField.sendKeys(messageText);
    }

    public void sendMessage() {
        sendMessageButton.click();
    }

    public boolean sendMessageAlertIsPresent() {
        return waits.expectVisibilityAndCheck(sendAlert);
    }

    public boolean visualTestIsPassed(EyesRunner runner) {
        TestResultsSummary allTestResults = runner.getAllTestResults();
        return allTestResults.getAllResults()[0].getTestResults().isPassed();
    }
}