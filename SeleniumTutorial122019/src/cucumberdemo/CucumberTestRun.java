package cucumberdemo;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome=true,
		features = "src/cucumberdemo/FeatureFolder",
		glue={"cucumberdemo/StepDefinitionFolder"}
		)

public class CucumberTestRun {

}
