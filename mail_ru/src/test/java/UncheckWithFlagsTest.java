import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "features/UncheckAllMessagesWithFlags.feature")
public class UncheckWithFlagsTest extends AbstractTestNGCucumberTests {}
