import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "features/MessageToSpam.feature")
public class MessageToSpamTest extends AbstractTestNGCucumberTests {}
