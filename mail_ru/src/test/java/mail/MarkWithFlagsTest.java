package mail;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "features/MarkMessagesWithFlags.feature")
public class MarkWithFlagsTest {}
