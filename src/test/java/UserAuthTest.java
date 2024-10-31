import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.StellarBurgerAPIService;
import ru.yandex.praktikum.pageobject.AuthPage;
import ru.yandex.praktikum.pageobject.ForgotPasswordPage;
import ru.yandex.praktikum.pageobject.MainPage;
import ru.yandex.praktikum.pageobject.RegistrationPage;

public class UserAuthTest {
    private WebDriver driver;
    private static RegistrationPage registrationPage;
    private static MainPage mainPage;
    private static AuthPage authPage;

    @Rule
    public TestName testName = new TestName();
    @Before
    public void setUp() {
        driver = WebDriverData.getWebDriver();
        registrationPage = new RegistrationPage(driver);
        mainPage = new MainPage(driver);
        authPage = new AuthPage(driver);
        registrationPage.openPage();
        registrationPage.fillingRegistrationForm("ninja", "ninja@yandex.ru", "ninja123");
        registrationPage.registrationButtonClick();
    }
    @Test
    public void userAuthFromMainPage() throws InterruptedException {
        mainPage.openPage();
        mainPage.mainPageAuthButtonClick();
        authPage.fillingAuthForm("ninja@yandex.ru", "ninja123");
        authPage.authButtonClick();
        Thread.sleep(1000);
        Assert.assertNotNull(((JavascriptExecutor) driver)
                .executeScript(String.format("return window.localStorage.getItem('accessToken');")).toString());
    }
    @Test
    public void userAuthFromMainPageViaPersonalAccount() throws InterruptedException {
        mainPage.openPage();
        mainPage.mainPagePersonalAccountButtonClick();
        authPage.fillingAuthForm("ninja@yandex.ru", "ninja123");
        authPage.authButtonClick();
        Thread.sleep(1000);
        Assert.assertNotNull(((JavascriptExecutor) driver)
                .executeScript(String.format("return window.localStorage.getItem('accessToken');")).toString());
    }
    @Test
    public void userAuthFromRegistrationPage() throws InterruptedException {
        registrationPage.openPage();
        registrationPage.authButtonClick();
        authPage.fillingAuthForm("ninja@yandex.ru", "ninja123");
        authPage.authButtonClick();
        Thread.sleep(1000);
        Assert.assertNotNull(((JavascriptExecutor) driver)
                .executeScript(String.format("return window.localStorage.getItem('accessToken');")).toString());
    }
    @Test
    public void userAuthFromForgotPasswordPage() throws InterruptedException {
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage.openPage();
        forgotPasswordPage.authButtonClick();
        authPage.fillingAuthForm("ninja@yandex.ru", "ninja123");
        authPage.authButtonClick();
        Thread.sleep(1000);
        Assert.assertNotNull(((JavascriptExecutor) driver)
                .executeScript(String.format("return window.localStorage.getItem('accessToken');")).toString());
    }
    @After
    public void closeDriverSession() throws InterruptedException {
        try {
            Thread.sleep(1000);
            new StellarBurgerAPIService().deleteUserResponse(((JavascriptExecutor) driver)
                    .executeScript(String.format("return window.localStorage.getItem('accessToken');")).toString());
        } catch (NullPointerException e){
            System.out.println("User not created in" + " " + testName.getMethodName() + " method");
        }
        driver.quit();
    }
}
