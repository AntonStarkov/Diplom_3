package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    private static final By REGISTRATION_NAME_FIELD = By.xpath("//*/label[text()='Имя']/parent::div/input");
    private static final By REGISTRATION_EMAIL_FIELD = By.xpath("//*/label[text()='Email']/parent::div/input");
    private static final By REGISTRATION_PASSWORD_FIELD = By.xpath("//*/label[text()='Пароль']/parent::div/input");
    private static final By REGISTRATION_BUTTON = By.xpath("//*/form/button[text()='Зарегистрироваться']");
    private static final By INCORRECT_PASSWORD_ERROR_TEXT = By.xpath("//*/div/p[text()='Некорректный пароль']");
    private static final By AUTH_BUTTON = By.xpath("//*/div/p/a[text()='Войти']");
    private static final String REGISTRATION_PAGE_URL = "https://stellarburgers.nomoreparties.site/register";
    private static final String REDIRECT_TO_LOGIN_PAGE_URL = "https://stellarburgers.nomoreparties.site/login";
    public RegistrationPage(WebDriver driver){
        this.driver = driver;
    }
    @Step("Opening registration page")
    public void openPage(){
        driver.get(REGISTRATION_PAGE_URL);
    }
    @Step("Filling out the registration form on the registration page")
    public void fillingRegistrationForm(String nameField, String emailField, String passwordField){
        driver.findElement(REGISTRATION_NAME_FIELD).sendKeys(nameField);
        driver.findElement(REGISTRATION_EMAIL_FIELD).sendKeys(emailField);
        driver.findElement(REGISTRATION_PASSWORD_FIELD).sendKeys(passwordField);
    }
    @Step("Click to \"Registration\" button on the registration page")
    public void registrationButtonClick(){
        driver.findElement(REGISTRATION_BUTTON).click();
    }
    @Step("Waiting for redirection to authorization page")
    public void getRedirectionToLoginPage(){
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.urlMatches(REDIRECT_TO_LOGIN_PAGE_URL));
    }
    @Step("Waiting for incorrect password error on the registration page")
    public void getIncorrectPasswordError(){
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.visibilityOfElementLocated(INCORRECT_PASSWORD_ERROR_TEXT));
    }
    @Step("Click to \"Authorization\" button on the registration page")
    public void authButtonClick(){
        driver.findElement(AUTH_BUTTON).click();
    }
}
