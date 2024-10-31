import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.StellarBurgerAPIService;
import ru.yandex.praktikum.pageobject.AuthPage;
import ru.yandex.praktikum.pageobject.RegistrationPage;


public class UserRegistrationTest {
    private WebDriver driver;
    private static RegistrationPage registrationPage;
    @Rule
    public TestName testName = new TestName();
    @Before
    public void setUp() {
        driver = WebDriverData.getWebDriver();
        registrationPage = new RegistrationPage(driver);
    }
    @Test
    public void registrationUser() {
        registrationPage.openPage();
        registrationPage.fillingRegistrationForm("ninja", "ninja@yandex.ru", "ninja123");
        registrationPage.registrationButtonClick();
        registrationPage.getRedirectionToLoginPage();
    }
    @Test
    public void registrationUserWithIncorrectPassword() {
        registrationPage.openPage();
        registrationPage.fillingRegistrationForm("ninja", "ninja@yandex.ru", "ajnin");
        registrationPage.registrationButtonClick();
        registrationPage.getIncorrectPasswordError();
    }
    @After
    public void closeDriverSession() throws InterruptedException {
        try {
            AuthPage authPage = new AuthPage(driver);
            authPage.openPage();
            authPage.fillingAuthForm("ninja@yandex.ru", "ninja123");
            authPage.authButtonClick();
            Thread.sleep(1000);
            new StellarBurgerAPIService().deleteUserResponse(((JavascriptExecutor) driver)
                    .executeScript(String.format("return window.localStorage.getItem('accessToken');")).toString());
        } catch (NullPointerException e){
            System.out.println("User not created in" + " " + testName.getMethodName() + " method");
        }
        driver.quit();
    }
}
