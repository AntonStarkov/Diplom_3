package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AuthPage {
    private final WebDriver driver;
    private static final By AUTH_EMAIL_FIELD = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[1]/div/div/input");
    private static final By AUTH_PASSWORD_FIELD = By.xpath("//*[@id=\"root\"]/div/main/div/form/fieldset[2]/div/div/input");
    private static final By AUTH_BUTTON = By.xpath("/html/body/div/div/main/div/form/button[text()='Войти']");
    private static final By AUTH_PAGE_PERSONAL_ACCOUNT_BUTTON = By.xpath("//*[@id=\"root\"]/div/header/nav/a/p[text()='Личный Кабинет']");
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
    @Step("Click to \"Personal account\" button on the authorization page")
    public void personalAccountButtonClick(){
        driver.findElement(AUTH_PAGE_PERSONAL_ACCOUNT_BUTTON).click();
    }
}
