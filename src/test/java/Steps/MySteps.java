package Steps;

import gherkin.lexer.Pa;
import org.openqa.selenium.By;
import utils.CustomMethods;
import utils.TestConstants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MySteps extends Base {
    CustomMethods customMethods = new CustomMethods();

    public void launchURL() {
        driver().manage().timeouts().implicitlyWait(TestConstants.IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        driver().get(TestConstants.URL);
    }

    public void loginUsing(String dataKey) throws IOException {

        String UserName = dataWorkbookManager.getReader().getRowDataAsMap(dataWorkbookManager.getWorkSheet(), dataKey).get("USER_ID");
        String Password = dataWorkbookManager.getReader().getRowDataAsMap(dataWorkbookManager.getWorkSheet(), dataKey).get("PASSWORD");

        driver().findElement(By.xpath("//*[text()='Hello, Sign in']")).click();

        driver().findElement(By.name("email")).sendKeys(UserName);

        driver().findElement(By.id("continue")).click();

        driver().findElement(By.name("password")).sendKeys(Password);

        driver().findElement(By.id("signInSubmit")).click();

        customMethods.waitForElement("twotabsearchtextbox", 20);

    }

    public void navigateToFundTransfer() {
        driver().findElement(By.xpath("//*[@data-testid='menu-expand-send-money']")).click();
        driver().findElement(By.xpath("//*[@data-testid='menu-link-fund-transfer']")).click();

    }
}
