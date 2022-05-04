package StepDefinitions;


import Steps.Base;
import Steps.MySteps;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import gherkin.ast.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import utils.DataWorkbookManager;
import utils.ExcelReader;
import utils.TestConstants;


public class MyStepdefs{
    MySteps mySteps = new MySteps();
    DataWorkbookManager dataWorkbookManager = new DataWorkbookManager();
    Base base=new Base();


    ThreadLocal<String> dataWorkbookName = new ThreadLocal<>();
    ThreadLocal<String> dataWorkSheetName = new ThreadLocal<>();

    @Before
    public void openBrowser() {
        mySteps.createDriverInstance();
    }

    @After
    public void closeBrowser() {
        mySteps.driver().quit();
    }

    @Given("^user is ready with Test Data excel \"([^\"]*)\" and sheet \"([^\"]*)\"$")
    public void userIsReadyWithTestDataExcelAndSheet(String strWorkBook, String strWorkSheet) throws Throwable {

        dataWorkbookName.set(strWorkBook);
        dataWorkSheetName.set(strWorkSheet);
        if (base.getBrowser().equalsIgnoreCase("chrome")) {
            dataWorkbookManager.setReader(new ExcelReader(TestConstants.DATA_FILE_PATH.replace("device\\workbookname", "Chrome\\" + dataWorkbookName.get()), "xlsx"));
        } else if (base.getBrowser().equalsIgnoreCase("edge")){
            dataWorkbookManager.setReader(new ExcelReader(TestConstants.DATA_FILE_PATH.replace("device\\workbookname", "Edge\\" + dataWorkbookName.get()), "xlsx"));
        }else if (base.getBrowser().equalsIgnoreCase("firefox")){
            dataWorkbookManager.setReader(new ExcelReader(TestConstants.DATA_FILE_PATH.replace("device\\workbookname", "Firefox\\" + dataWorkbookName.get()), "xlsx"));
        }
        dataWorkbookManager.setWorkSheet(dataWorkbookManager.getReader().getSheet(dataWorkSheetName.get()));
    }


    @Given("^user launch url$")
    public void userLaunchUrl() {
        mySteps.launchURL();
    }


    @Then("^user login using username and password using dataKey as \"([^\"]*)\"$")
    public void userEnterUsernameAndPasswordUsingDataKeyAs(String dataKey) throws Throwable {
        mySteps.loginUsing(dataKey);
    }


    @Given("^user launch browser$")
    public void userLaunchBrowser() {

        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");

        WebDriver webDriver=new ChromeDriver();

    }
}
