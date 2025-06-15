package utils;

import DriverManager.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class CustomMethods extends DriverManager {

    private int stepCounter;
    private static ThreadLocal<Integer> seleniumCounter = ThreadLocal.withInitial(() -> {
        return 0;
    });



    public void setImplicitTimeout(int Seconds)
    {
        driver().manage().timeouts().implicitlyWait(Duration.ofSeconds(Seconds));
    }


    public void waitForElement(String elementLocator,int Seconds) throws IOException {
        WebDriverWait wait=new WebDriverWait(driver(), Duration.ofSeconds(Seconds));
        wait.until(ExpectedConditions.visibilityOf(driver().findElement(getElementLocatorFromProperties(elementLocator))));
    }


    public void waitForElementAndClick(String elementLocator,int Seconds) throws IOException {
        WebDriverWait wait=new WebDriverWait(driver(),  Duration.ofSeconds(Seconds));
        wait.until(ExpectedConditions.visibilityOf(driver().findElement(getElementLocatorFromProperties(elementLocator)))).click();
    }

    public void takescreenshot(WebDriver driver) throws IOException {

        ++this.stepCounter;
        int seleniumStepCount = getSeleniumCounter();
        seleniumCounter.set(seleniumStepCount + 1);

        TakesScreenshot ts = (TakesScreenshot)driver;

        File takenScreenShot = ts.getScreenshotAs(OutputType.FILE);

        StringBuilder screenshotFolder = new StringBuilder()
                .append(System.getProperty("user.dir"))
                .append(File.separator)
                .append("Batch_")
                .append(File.separator)
                .append(Thread.currentThread().getName())
                .append(File.separator)
                .append("Screenshots")
                .append(File.separator)
                .append(File.separator);

        File screenShotFolder = new File(screenshotFolder.toString());

        if (!screenShotFolder.exists()) {
            System.out.println("Created screenshot folder as it doesn't exist.");
        }

        String destinationPath = screenshotFolder
                .append(File.separator)
                .append("_")
                .append("_Step").append(getSeleniumCounter())
                .append(".png")
                .toString();
        File destScreenShotFile = new File(destinationPath);
        FileUtils.copyFile(takenScreenShot, destScreenShotFile, false);


    }
    static int getSeleniumCounter() {
        return (Integer)seleniumCounter.get();
    }

}
