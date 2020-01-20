import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "features/MarkMessagesWithFlags.feature")
public class MarkWithFlagsTest extends AbstractTestNGCucumberTests {
}
