package StepDefinitions;


import Runner.Runner;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.openqa.selenium.By;

public class MyStepdefs extends Base{
Runner runner=new Runner();
    @Before
    public void openBrowser() {
        driver = Base.openBrowser(browser);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }


    @Given("^user launch url$")
    public void userLaunchUrl() {
        driver.get("https://conv.rakbankonline.ae/IBRetailTest/auth");
    }


    @Then("^user enter username$")
    public void userEnterUsername() {
        driver.findElement(By.xpath("//input[@data-testid='username-input']")).sendKeys("UserName");

    }
}
