package Steps;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import utils.CustomMethods;
import utils.DataWorkbookManager;
import utils.ReadPropertiesFile;
import utils.TestConstants;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Base {

    DataWorkbookManager dataWorkbookManager = new DataWorkbookManager();
    ReadPropertiesFile readPropertiesFile = new ReadPropertiesFile();


    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    private static ThreadLocal<String> browsers = new ThreadLocal<>();


    public void setBrowser(String browser) {
        this.browsers.set(browser);
    }

    public String getBrowser() {
        return this.browsers.get();
    }

    public void createDriverInstance() {
        if (getBrowser().equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            this.driver.set(new ChromeDriver());
        } else if (getBrowser().equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            this.driver.set(new EdgeDriver());
        } else {
            Assert.fail(getBrowser() + " is not supported");
        }
        this.driver().manage().window().maximize();
        this.driver().manage().timeouts().implicitlyWait(TestConstants.IMPLICIT_TIMEOUT, TimeUnit.SECONDS);

    }

    public WebDriver driver() {
        return this.driver.get();
    }




    public By getElementLocatorFromProperties(String elementLocatorName) throws IOException {
        By elementLocator = null;
        String temp = readPropertiesFile.returnPropertyValue(elementLocatorName);

        if (temp.startsWith("xpath")) {
            temp = temp.replace("xpath=", "");
            elementLocator = By.xpath(temp);
        } else if (temp.startsWith("className")) {
            temp = temp.replace("className=", "");
            elementLocator = By.className(temp);
        }else if (temp.startsWith("name")) {
            temp = temp.replace("name=", "");
            elementLocator = By.name(temp);
        }else if (temp.startsWith("tagName")) {
            temp = temp.replace("tagName=", "");
            elementLocator = By.tagName(temp);
        }else if (temp.startsWith("id")) {
            temp = temp.replace("id=", "");
            elementLocator = By.id(temp);
        }else if (temp.startsWith("linkText")) {
            temp = temp.replace("linkText=", "");
            elementLocator = By.partialLinkText(temp);
        }else if (temp.startsWith("partialLinkText")) {
            temp = temp.replace("partialLinkText=", "");
            elementLocator = By.partialLinkText(temp);
        }
        else
            Assert.fail("This funtion only supports {xpath, className, name, tagName, id, linkText, partialLinkText}");

        return elementLocator;
    }
}
