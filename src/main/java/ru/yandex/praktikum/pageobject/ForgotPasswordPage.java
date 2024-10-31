package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private final WebDriver driver;
    private static final String FORGOT_PASSWORD_PAGE = "https://stellarburgers.nomoreparties.site/forgot-password";
    private static final By AUTH_BUTTON = By.xpath("//*[@id=\"root\"]/div/main/div/div/p/a[text()='Войти']");
    public ForgotPasswordPage(WebDriver driver){
        this.driver = driver;
    }
    @Step("Opening password recovery page")
    public void openPage(){
        driver.get(FORGOT_PASSWORD_PAGE);
    }
    @Step("Click to \"Authorization\" button on the password recovery page")
    public void authButtonClick(){
        driver.findElement(AUTH_BUTTON).click();
    }
}
