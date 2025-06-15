package Steps;

import DriverManager.DriverManager;
import org.openqa.selenium.By;
import utils.DataWorkbookManager;
import utils.TestConstants;

import java.io.IOException;
import java.time.Duration;

public class LoginPage extends DriverManager {
    DataWorkbookManager dataWorkbookManager = new DataWorkbookManager();

    public void launchURL() {
        driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(TestConstants.IMPLICIT_TIMEOUT));
        driver().get(TestConstants.URL);
    }

    public void loginUsing(String dataKey) {

        String UserName = dataWorkbookManager.getReader().getRowDataAsMap(dataWorkbookManager.getWorkSheet(), dataKey).get("USER_ID");
        String Password = dataWorkbookManager.getReader().getRowDataAsMap(dataWorkbookManager.getWorkSheet(), dataKey).get("PASSWORD");

        System.out.println(UserName);
        System.out.println(Password);
//        driver().findElement(By.xpath("//*[text()='Hello, Sign in']")).click();
//
//        driver().findElement(By.name("email")).sendKeys(UserName);
//
//        driver().findElement(By.id("continue")).click();
//
//        driver().findElement(By.name("password")).sendKeys(Password);

//        driver().findElement(By.id("signInSubmit")).click();
//
//        customMethods.waitForElement("twotabsearchtextbox", 20);

    }

    public void navigateToFundTransfer() {
        driver().findElement(By.xpath("//*[@data-testid='menu-expand-send-money']")).click();
        driver().findElement(By.xpath("//*[@data-testid='menu-link-fund-transfer']")).click();

    }
}
