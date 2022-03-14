package Runner;

import Steps.Base;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.*;


@CucumberOptions(
        features = "src/test/FeatureFiles/Tests2.feature",
        glue = {"StepDefinitions"},
        format = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty"
        },
        monochrome = true
)
public class Tests2Runner extends AbstractTestNGCucumberTests {

    private TestNGCucumberRunner testNGCucumberRunner;




    @BeforeClass(alwaysRun = true)
    public void setUpClass() {
        this.testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }

    @AfterClass(alwaysRun = true)
    public void tearDownClass() {
        this.testNGCucumberRunner.finish();

    }

    @Test(dataProvider = "provideFeatures", alwaysRun = true)
    public void executeFeature(CucumberFeatureWrapper cucumberFeature) {
        this.testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
    }


    @DataProvider
    public Object[][] provideFeatures() {
        return this.testNGCucumberRunner.provideFeatures();
    }


    Base base = new Base();

    @BeforeClass(alwaysRun = true)
    @Parameters({"browser"})
    public void setBrowserName(String browser) {
        base.setBrowser(browser);
    }

}
