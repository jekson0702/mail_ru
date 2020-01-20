package Waits;

import SingletonWebDriver.SingletonWebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Waits {
    private static final int TIMEOUT = 10;
    private WebDriver driver = SingletonWebDriver.getDriver();
    private JavascriptExecutor executor = (JavascriptExecutor) driver;

    public WebElement expectClickable(WebElement webElement) {
        return new WebDriverWait(SingletonWebDriver.getDriver(), TIMEOUT)
                .until(ExpectedConditions.elementToBeClickable(webElement));
    }

    public void expectVisibility(WebElement webElement) {
        new WebDriverWait(driver, TIMEOUT).
                until(ExpectedConditions.visibilityOf(webElement));
    }

    public boolean expectVisibilityAndCheck(WebElement webElement) {
        return new WebDriverWait(driver, TIMEOUT).
                until(ExpectedConditions.visibilityOf(webElement)).isDisplayed();
    }

    public void expectVisibilityOfAllElements(List<WebElement> list) {
        new WebDriverWait(driver, TIMEOUT).
                until(ExpectedConditions.visibilityOfAllElements(list));
    }

    public void expectClickableAndClick(WebElement webElement) {
        new WebDriverWait(driver, TIMEOUT).until(ExpectedConditions.elementToBeClickable(webElement));
        executor.executeScript("arguments[0].click()", webElement);
    }
}