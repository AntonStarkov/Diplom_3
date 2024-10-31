package ru.yandex.praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private static final String MAIN_PAGE = "https://stellarburgers.nomoreparties.site";
    private static final By MAIN_PAGE_AUTH_BUTTON = By.xpath("//*[@id=\"root\"]/div/main/section[2]/div/button[text()='Войти в аккаунт']");
    private static final By MAIN_PAGE_PERSONAL_ACCOUNT_BUTTON = By.xpath("//*[@id=\"root\"]/div/header/nav/a/p[text()='Личный Кабинет']");
    private static final By MAIN_PAGE_BUNS_BUTTON = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div/span[text()='Булки']/parent::div");
    private static final By MAIN_PAGE_SAUCES_BUTTON = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div/span[text()='Соусы']/parent::div");
    private static final By MAIN_PAGE_FILLINGS_BUTTON = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[1]/div/span[text()='Начинки']/parent::div");
    private static final By SPICY_X_SAUCE = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/ul[2]/a[@href=\"/ingredient/61c0c5a71d1f82001bdaaa72\"]");
    private static final By FLUORESCENT_BUN = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/ul[1]/a[@href=\"/ingredient/61c0c5a71d1f82001bdaaa6d\"]");
    private static final By MOLLUSKS_MEAT = By.xpath("//*[@id=\"root\"]/div/main/section[1]/div[2]/ul[3]/a[@href=\"/ingredient/61c0c5a71d1f82001bdaaa6f\"]");
    public MainPage(WebDriver driver){
        this.driver = driver;
    }
    @Step("Opening main page")
    public void openPage(){
        driver.get(MAIN_PAGE);
    }
    @Step("Click to \"Login to account\" button on the main page")
    public void mainPageAuthButtonClick(){
        driver.findElement(MAIN_PAGE_AUTH_BUTTON).click();
    }
    @Step("Click to \"Buns\" button on the main page")
    public void mainPageBunsButtonClick(){
        driver.findElement(MAIN_PAGE_BUNS_BUTTON).click();
    }
    @Step("Waiting for scroll to buns category on the main page")
    public void waitingForScrollToBuns(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> {
                    return (d.findElement(FLUORESCENT_BUN).getRect().y < 400) & (d.findElement(FLUORESCENT_BUN).getRect().y > 200);
                });
    }
    @Step("Click to \"Sauces\" button on the main page")
    public void mainPageSaucesButtonClick(){
        driver.findElement(MAIN_PAGE_SAUCES_BUTTON).click();
    }
    @Step("Waiting for scroll to sauces category on the main page")
    public void waitingForScrollToSauces(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> {
                    return (d.findElement(SPICY_X_SAUCE).getRect().y < 400) & (d.findElement(SPICY_X_SAUCE).getRect().y > 200);
                });
    }
    @Step("Click to \"Filling\" button on the main page")
    public void mainPageFillingsButtonClick(){
        driver.findElement(MAIN_PAGE_FILLINGS_BUTTON).click();
    }
    @Step("Waiting for scroll to fillings category on the main page")
    public void waitingForScrollToFillings(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(d -> {
                    return (d.findElement(MOLLUSKS_MEAT).getRect().y < 400) & (d.findElement(MOLLUSKS_MEAT).getRect().y > 200);
                });
    }
    @Step("Click to \"Personal account\" button on the main page")
    public void mainPagePersonalAccountButtonClick(){
        driver.findElement(MAIN_PAGE_PERSONAL_ACCOUNT_BUTTON).click();
    }
}
