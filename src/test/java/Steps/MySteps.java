package Steps;

import org.openqa.selenium.By;
import utils.CustomMethods;
import utils.TestConstants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class MySteps extends Base{
    CustomMethods customMethods =new CustomMethods();

    public void launchURL() {
        driver().manage().timeouts().implicitlyWait(TestConstants.IMPLICIT_TIMEOUT, TimeUnit.SECONDS);
        driver().get("https://conv.rakbankonline.ae/IBRetailTest/auth");
    }

    public void loginUsing(String dataKey) throws IOException {
        String UserName = dataWorkbookManager.getReader().getRowDataAsMap(dataWorkbookManager.getWorkSheet(), dataKey).get("USER_ID");
        String Password = dataWorkbookManager.getReader().getRowDataAsMap(dataWorkbookManager.getWorkSheet(), dataKey).get("PASSWORD");

        driver().findElement(By.name("username")).sendKeys(UserName);
        driver().findElement(By.name("password")).sendKeys(Password);
//        driver().findElement(By.xpath("//*[@data-testid='button-login']")).click();
        customMethods.waitForElementAndClick("LoginButton",30);
    }

    public void navigateToFundTransfer() {
        driver().findElement(By.xpath("//*[@data-testid='menu-expand-send-money']")).click();
        driver().findElement(By.xpath("//*[@data-testid='menu-link-fund-transfer']")).click();

    }
}
