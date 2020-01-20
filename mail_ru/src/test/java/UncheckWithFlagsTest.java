import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "features/UncheckAllMessagesWithFlags.feature")
public class UncheckWithFlagsTest {}
