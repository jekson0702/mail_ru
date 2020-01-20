package pages;

import SingletonWebDriver.SingletonWebDriver;
import Waits.Waits;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.TestResultsSummary;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import parser.DomParser;
import parser.MessageData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public class SendMessagePage {
    private Waits waits = new Waits();
    private Logger logger = Logger.getLogger(SendMessagePage.class);
    private MessageData messageData = new MessageData();
    private static final String MESSAGEDATA_XML = "messagedata.xml";
    private List<MessageData> messageDataList;

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
        PageFactory.initElements(SingletonWebDriver.getDriver(), this);
        getDataFromXml();
    }

    public void clickWriteMessageButton() {
        waits.expectClickableAndClick(writeMessageButton);
        logger.info("click Write Message button");
    }

    public void enterAddress() {
        waits.expectClickable(emailInput);
        emailInput.sendKeys(messageData.getAddress());
        logger.info("email addresses are entered");
    }

    public void enterMessageText() {
        messageTextField.sendKeys(messageData.getMessageText());
        logger.info("message text is entered");
    }

    public void sendMessage() {
        sendMessageButton.click();
        logger.info("click Send message button");
    }

    public boolean visualTestIsPassed(EyesRunner runner) {
        TestResultsSummary allTestResults = runner.getAllTestResults();
        return allTestResults.getAllResults()[0].getTestResults().isPassed();
    }

    public void getDataFromXml() {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document document = dBuilder.parse(MESSAGEDATA_XML);
            messageDataList = new DomParser().parse(document);
            messageData = messageDataList.get(0);
        } catch (IOException | SAXException
                | ParserConfigurationException | XMLStreamException exception) {
        }
    }
}