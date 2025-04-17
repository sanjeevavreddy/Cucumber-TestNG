package Runner;

import Steps.DriverManager;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.*;


@CucumberOptions(
        features = "src/test/FeatureFiles",
        glue = {"StepDefinitions"},
        plugin = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty/report.html"
        },
        monochrome = true
)
public class TestsRunner extends AbstractTestNGCucumberTests {

    DriverManager driverManager = new DriverManager();

    @BeforeClass(alwaysRun = true)
    @Parameters({"browser"})
    public void setBrowserName(String browser) {
        driverManager.setBrowser(browser);
    }

}
