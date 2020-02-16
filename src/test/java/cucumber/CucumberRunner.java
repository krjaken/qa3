package cucumber;


import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/cucumber",
        glue = "src/test/java/cucumber",
        tags = "@Smoke")
public class CucumberRunner {
}
