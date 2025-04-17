package StepDefinitions;


import Steps.DriverManager;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

import utils.DataWorkbookManager;
import utils.ExcelReader;
import utils.TestConstants;


public class LoginPage {
    Steps.LoginPage loginPage = new Steps.LoginPage();
    DataWorkbookManager dataWorkbookManager = new DataWorkbookManager();
    DriverManager driverManager = new DriverManager();


    ThreadLocal<String> dataWorkbookName = new ThreadLocal<>();
    ThreadLocal<String> dataWorkSheetName = new ThreadLocal<>();

    @Before
    public void openBrowser() {
        loginPage.createDriverInstance();
    }

    @After
    public void closeBrowser() {
        loginPage.driver().quit();
    }

    @Given("^user is ready with Test Data excel \"([^\"]*)\" and sheet \"([^\"]*)\"$")
    public void userIsReadyWithTestDataExcelAndSheet(String strWorkBook, String strWorkSheet) throws Throwable {

        dataWorkbookName.set(strWorkBook);
        dataWorkSheetName.set(strWorkSheet);
        if (driverManager.getBrowser().equalsIgnoreCase("chrome")) {
            dataWorkbookManager.setReader(new ExcelReader(TestConstants.DATA_FILE_PATH.replace("device/workbookname", "Chrome/" + dataWorkbookName.get()), "xlsx"));
        } else if (driverManager.getBrowser().equalsIgnoreCase("edge")) {
            dataWorkbookManager.setReader(new ExcelReader(TestConstants.DATA_FILE_PATH.replace("device/workbookname", "Edge/" + dataWorkbookName.get()), "xlsx"));
        } else if (driverManager.getBrowser().equalsIgnoreCase("firefox")) {
            dataWorkbookManager.setReader(new ExcelReader(TestConstants.DATA_FILE_PATH.replace("device/workbookname", "Firefox/" + dataWorkbookName.get()), "xlsx"));
        }
        dataWorkbookManager.setWorkSheet(dataWorkbookManager.getReader().getSheet(dataWorkSheetName.get()));
    }


    @Given("^user launch url$")
    public void userLaunchUrl() throws InterruptedException {
        loginPage.launchURL();
    }


    @Then("^user login using username and password using dataKey as \"([^\"]*)\"$")
    public void userEnterUsernameAndPasswordUsingDataKeyAs(String dataKey) throws Throwable {
        loginPage.loginUsing(dataKey);
    }


}
