package StepDefinitions;


import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

public class Base {
    public static WebDriver driver;
    public static String browser;


    public static WebDriver openBrowser(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome":
                System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
                driver = new ChromeDriver();
                System.out.println("Chrome Driver Invoked");
                break;
            case "edge":
                System.setProperty("webdriver.edge.driver", "C:\\msedgedriver.exe");
                driver = new EdgeDriver();
                System.out.println("Edge Driver Invoked");
                break;
            default:
                Assert.fail(browser + " is not available");
        }
        return driver;
    }

}
