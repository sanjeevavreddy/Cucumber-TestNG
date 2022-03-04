package Runner;

import StepDefinitions.Base;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;
import org.testng.annotations.*;


@CucumberOptions(
        features = "src/test/FeatureFiles/Sample.feature",
        glue = {"StepDefinitions"},
        format = {
                "pretty",
                "html:target/cucumber-reports/cucumber-pretty"
        },
        monochrome = true
)
public class Runner extends AbstractTestNGCucumberTests {

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


    Base base=new Base();
    @BeforeTest(alwaysRun = true)
    @Parameters({"browser"})
    public void setBrowser(String browser) {
        base.browser=browser;
    }

}
