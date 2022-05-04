package utils;

import Steps.Base;
import cucumber.api.Scenario;
import gherkin.ast.Feature;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class CustomMethods extends Base {

    private int stepCounter;
    private static ThreadLocal<Integer> seleniumCounter = ThreadLocal.withInitial(() -> {
        return 0;
    });



    public void setImplicitTimeout(int Seconds)
    {
        driver().manage().timeouts().implicitlyWait(Seconds, TimeUnit.SECONDS);
    }


    public void waitForElement(String elementLocator,int Seconds) throws IOException {
        WebDriverWait wait=new WebDriverWait(driver(), Seconds);
        wait.until(ExpectedConditions.visibilityOf(driver().findElement(getElementLocatorFromProperties(elementLocator))));
    }


    public void waitForElementAndClick(String elementLocator,int Seconds) throws IOException {
        WebDriverWait wait=new WebDriverWait(driver(), Seconds);
        wait.until(ExpectedConditions.visibilityOf(driver().findElement(getElementLocatorFromProperties(elementLocator)))).click();
    }

//    public void takescreenshot(RemoteWebDriver driver) throws IOException {
//        TakesScreenshot takesScreenshot = driver;
//
//        ++this.stepCounter;
//        int seleniumStepCount = getSeleniumCounter();
//        seleniumCounter.set(seleniumStepCount + 1);
//
//        File takenScreenShot = takesScreenshot.getScreenshotAs(OutputType.FILE);
//
//        StringBuilder screenshotFolder = (new StringBuilder()).append(System.getProperty("user.dir").append(File.separator).append("Batch_").append(FeatureListener.getBatchCount()).append(File.separator).append(Thread.currentThread().getName()).append(File.separator).append("Screenshots").append(File.separator).append(ExtentListener.getFeatureName()).append(File.separator).append(ExtentListener.getScenarioName());
//
//        File screenShotFolder = new File(screenshotFolder.toString());
//
//        if (!screenShotFolder.exists()) {
//            System.out.println("Created screenshot folder as it doesn't exist.");
//        }
//
//        String destinationPath = screenshotFolder.append(File.separator).append(ExtentListener.getScenarioName()).append("_" + ExtentListener.getExampleName()).append("_Step").append(getSeleniumCounter()).append(".png").toString();
//        File destScreenShotFile = new File(destinationPath);
//        FileUtils.copyFile(takenScreenShot, destScreenShotFile, false);
//
//
//    }
//    static int getSeleniumCounter() {
//        return (Integer)seleniumCounter.get();
//    }

}
