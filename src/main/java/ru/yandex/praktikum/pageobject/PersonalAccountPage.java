package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {
    private final WebDriver driver;
    private static final By EXIT_BUTTON = By.xpath("//*[@id=\"root\"]/div/main/div/nav/ul/li[3]/button[text()='Выход']");
    private static final By LOGO_BUTTON = By.xpath("//*[@id=\"root\"]/div/header/nav/div");
    private static final By CONSTRUCTOR_BUTTON = By.xpath("//*[@id=\"root\"]/div/header/nav/ul/li[1]/a/p[text()='Конструктор']");
    private static final String MAIN_PAGE = "https://stellarburgers.nomoreparties.site";
    public PersonalAccountPage(WebDriver driver){
        this.driver = driver;
    }
    @Step("Waiting for exit button on the personal account page")
    public void waitForExitButton(){
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.visibilityOfElementLocated(EXIT_BUTTON));
    }
    @Step("Click to \"Exit\" button on the personal account page")
    public void exitButtonClick(){
        driver.findElement(EXIT_BUTTON).click();
    }
    @Step("Click to logo on the personal account page")
    public void logoButtonClick(){
        driver.findElement(LOGO_BUTTON).click();
    }
    @Step("Click to \"Constructor\" button on the personal account page")
    public void constructorButtonClick(){
        driver.findElement(CONSTRUCTOR_BUTTON).click();
    }
    @Step("Waiting for redirection to main page")
    public void getRedirectionToMainPage(){
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(ExpectedConditions.urlMatches(MAIN_PAGE));
    }
}
