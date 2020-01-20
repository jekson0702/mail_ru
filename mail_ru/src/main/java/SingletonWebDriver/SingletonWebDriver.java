package SingletonWebDriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SingletonWebDriver {
    private static WebDriver driver;

    private SingletonWebDriver() {
    }

    public static WebDriver getDriver() {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        if (null == driver) {
            driver = new ChromeDriver();
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void quitDriver() {
        if (null != driver) {
            driver.quit();
            driver = null;
        }
    }
}