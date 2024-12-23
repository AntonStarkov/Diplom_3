import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.StellarBurgerAPIService;
import ru.yandex.praktikum.pageobject.AuthPage;
import ru.yandex.praktikum.pageobject.ForgotPasswordPage;
import ru.yandex.praktikum.pageobject.MainPage;
import ru.yandex.praktikum.pageobject.RegistrationPage;
import ru.yandex.praktikum.pojo.createuser.CreateUserDeserialization;

public class UserAuthTest {
    private WebDriver driver;
    private static RegistrationPage registrationPage;
    private static MainPage mainPage;
    private static AuthPage authPage;
    private String accessToken;
    private StellarBurgerAPIService stellarBurgerAPIService;

    @Rule
    public TestName testName = new TestName();
    @Before
    public void setUp() {
        driver = WebDriverData.getWebDriver();
        registrationPage = new RegistrationPage(driver);
        mainPage = new MainPage(driver);
        authPage = new AuthPage(driver);
        stellarBurgerAPIService = new StellarBurgerAPIService();
        accessToken = stellarBurgerAPIService.createUserResponse()
                .as(CreateUserDeserialization.class).getAccessToken();
    }
    @Test
    @DisplayName("Authorization via \"Войти в аккаунт\" from main page")
    @Description("Test for authorization from main page after click to \"Войти в аккаунт\" button")
    public void userAuthFromMainPage() {
        mainPage.openPage();
        mainPage.mainPageAuthButtonClick();
        authPage.fillingAuthForm("ninja@yandex.ru", "ninja123");
        authPage.authButtonClick();
        authPage.waitRedirectionToMainPage();
        Assert.assertNotNull(((JavascriptExecutor) driver)
                .executeScript(String.format("return window.localStorage.getItem('accessToken');")).toString());
    }
    @Test
    @DisplayName("Authorization via \"Личный кабинет\" from main page")
    @Description("Test for authorization from main page after click to \"Личный кабинет\" button")
    public void userAuthFromMainPageViaPersonalAccount() {
        mainPage.openPage();
        mainPage.mainPagePersonalAccountButtonClick();
        authPage.fillingAuthForm("ninja@yandex.ru", "ninja123");
        authPage.authButtonClick();
        authPage.waitRedirectionToMainPage();
        Assert.assertNotNull(((JavascriptExecutor) driver)
                .executeScript(String.format("return window.localStorage.getItem('accessToken');")).toString());
    }
    @Test
    @DisplayName("Authorization from registration page")
    @Description("Test for authorization from registration page after click to \"Войти\" button")
    public void userAuthFromRegistrationPage() {
        registrationPage.openPage();
        registrationPage.authButtonClick();
        authPage.fillingAuthForm("ninja@yandex.ru", "ninja123");
        authPage.authButtonClick();
        authPage.waitRedirectionToMainPage();
        Assert.assertNotNull(((JavascriptExecutor) driver)
                .executeScript(String.format("return window.localStorage.getItem('accessToken');")).toString());
    }
    @Test
    @DisplayName("Authorization from forgot password page")
    @Description("Test for authorization from forgot password page after click to \"Войти\" button")
    public void userAuthFromForgotPasswordPage() {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.openPage();
        forgotPasswordPage.authButtonClick();
        authPage.fillingAuthForm("ninja@yandex.ru", "ninja123");
        authPage.authButtonClick();
        authPage.waitRedirectionToMainPage();
        Assert.assertNotNull(((JavascriptExecutor) driver)
                .executeScript(String.format("return window.localStorage.getItem('accessToken');")).toString());
    }
    @After
    public void closeDriverSession() {
        try {
            stellarBurgerAPIService.deleteUserResponse(accessToken);
        } catch (NullPointerException e){
            System.out.println("User not created in" + " " + testName.getMethodName() + " method");
        }
        driver.quit();
    }
}
