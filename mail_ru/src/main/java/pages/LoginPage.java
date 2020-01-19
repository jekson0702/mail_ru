package pages;

import SingletonWebDriver.SingletonWebDriver;
import Waits.Waits;

import configuration.Configuration;
import dataBase.User;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    private Waits waits = new Waits();
    private User user;

    @FindBy(id = "mailbox:login")
    private WebElement loginField;

    @FindBy(id = "mailbox:password")
    private WebElement passwordField;

    @FindBy(id = "mailbox:password")
    private WebElement emailField;

    @FindBy(xpath = "//*[@id='mailbox:submit']/input")
    private WebElement buttonEnter;

    @FindBy(xpath = "//*[@id='PH_logoutLink']")
    private WebElement logoutLink;

    @FindBy(xpath = "//div[contains(text(),'Неверное имя ящика')]")
    private WebElement wrongLoginError;

    @FindBy(xpath = "//div[contains(text(),'Неверное имя или пароль')]")
    private WebElement wrongPasswordError;

    public LoginPage() {
        PageFactory.initElements(SingletonWebDriver.getDriver(), this);
    }

    public void loadMainPage() {
        SingletonWebDriver.getDriver().get(Configuration.getMainUrl());
    }

    public void loginAsCorrectUser() {
        user = new User("rightLoginAndPassword");
        enterLogin(user.getLogin());
        enterPassword(user.getPassword());
    }

    public void authorizationWithWrongLogin() {
        user = new User("wrongLogin");
        enterLogin(user.getLogin());
    }

    public void authorizationWithWrongPassword() {
        user = new User("wrongPassword");
        enterLogin(user.getLogin());
        enterPassword(user.getPassword());
    }

    public void enterLogin(String login) {
        loginField.clear();
        loginField.sendKeys(login);
        clickEnterButton();
    }

    public void enterPassword(String password) {
        waits.expectClickable(passwordField);
        passwordField.clear();
        passwordField.sendKeys(password);
        clickEnterButton();
    }

    public void clickEnterButton() {
        buttonEnter.click();
    }

    public boolean logoutLinkPresents() {
        return elementIsPresent(logoutLink);
    }

    public boolean wrongLoginErrorIsPresent() {
        return elementIsPresent(wrongLoginError);
    }

    public boolean wrongPasswordErrorIsPresent() {
        return elementIsPresent(wrongPasswordError);
    }

    public boolean elementIsPresent(WebElement element) {
        return waits.expectClickable(element).isDisplayed();
    }
}