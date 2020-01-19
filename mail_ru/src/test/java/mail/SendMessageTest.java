package mail;

import SingletonWebDriver.SingletonWebDriver;
import com.applitools.eyes.EyesRunner;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.RectangleSize;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import configuration.Configuration;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import pages.LoginPage;
import pages.SendMessagePage;
import parser.DomParser;
import parser.MessageData;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.List;

public class SendMessageTest {
    private SendMessagePage sendMessagePage;
    private LoginPage loginPage;
    private WebDriver driver;
    private static final String MESSAGEDATA_XML = "messagedata.xml";
    private MessageData messageData;
    private List<MessageData> messageDataList;
    private String addresses;
    private String messageText;
    private EyesRunner runner;
    private Eyes eyes;
    private String apiKey = Configuration.getApiKey();


    @BeforeClass
    public void createDriver() throws SAXException, XMLStreamException,
            ParserConfigurationException, IOException {
        driver = SingletonWebDriver.getDriver();
        loginPage = new LoginPage();
        sendMessagePage = new SendMessagePage();
        getDataFromXml();
    }

    @Test
    public void sendMessageWithApplitools() {
        runner = new ClassicRunner();
        eyes = new Eyes(runner);
        eyes.setApiKey(apiKey);
        eyes.open(driver, "MailRu", "SendMessageTest", new RectangleSize(1400, 600));
        eyes.setMatchLevel(MatchLevel.CONTENT);
        loginPage.loadMainPage();
        eyes.checkWindow("MainPage");
        loginPage.loginAsCorrectUser();
        sendMessagePage.enterAddress(addresses);
        sendMessagePage.enterMessageText(messageText);
        eyes.checkWindow("ScreenBeforeSending");
        sendMessagePage.sendMessage();
        eyes.closeAsync();
        SingletonWebDriver.quitDriver();
        eyes.abortIfNotClosed();
        Assert.assertTrue(sendMessagePage.visualTestIsPassed(runner));
    }

    @AfterClass
    public void afterClass() {
        SingletonWebDriver.quitDriver();
    }

    public void getDataFromXml() throws IOException, XMLStreamException,
            SAXException, ParserConfigurationException {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document document = dBuilder.parse(MESSAGEDATA_XML);
        messageDataList = new DomParser().parse(document);
        messageData = messageDataList.get(0);
        addresses = messageData.getAddress();
        messageText = messageData.getMessageText();
    }
}