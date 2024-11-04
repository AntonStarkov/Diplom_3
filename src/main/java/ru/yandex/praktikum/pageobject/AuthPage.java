package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthPage {
    private final WebDriver driver;
    private static final By AUTH_EMAIL_FIELD = By.xpath("//*/div/div/input[@name='name']");
    private static final By AUTH_PASSWORD_FIELD = By.xpath("//*/div/div/input[@name='Пароль']");
    private static final By AUTH_BUTTON = By.xpath("//*/form/button[text()='Войти']");
    private static final By AUTH_PAGE_PERSONAL_ACCOUNT_BUTTON = By.xpath("//*/nav/a/p[text()='Личный Кабинет']");
    private static final String MAIN_PAGE_REDIRECTION = "https://stellarburgers.nomoreparties.site/";
    private static final String AUTH_PAGE = "https://stellarburgers.nomoreparties.site/login";
    public AuthPage(WebDriver driver){
        this.driver = driver;
    }
    public void openPage(){
        driver.get(AUTH_PAGE);
    }
    @Step("Filling out the authorization form on the authorization page")
    public void fillingAuthForm(String emailField, String passwordField){
        driver.findElement(AUTH_EMAIL_FIELD).sendKeys(emailField);
        driver.findElement(AUTH_PASSWORD_FIELD).sendKeys(passwordField);
    }
    @Step("Click to \"Authorization\" button on the authorization page")
    public void authButtonClick(){
        driver.findElement(AUTH_BUTTON).click();
    }
    @Step("Waiting for redirection to authorization page")
    public void getRedirectionToLoginPage(){
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.urlMatches(AUTH_PAGE));
    }
    @Step("Waiting for redirection to main page")
    public void waitRedirectionToMainPage(){
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.urlToBe(MAIN_PAGE_REDIRECTION));
    }
    @Step("Click to \"Personal account\" button on the authorization page")
    public void personalAccountButtonClick(){
        driver.findElement(AUTH_PAGE_PERSONAL_ACCOUNT_BUTTON).click();
    }
}
