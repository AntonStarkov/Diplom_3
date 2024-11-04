import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.StellarBurgerAPIService;
import ru.yandex.praktikum.pageobject.RegistrationPage;
import ru.yandex.praktikum.pojo.loginuser.LoginUserDeserialization;


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
    @DisplayName("Registration user")
    @Description("Test for registration user with correct data")
    public void registrationUser() {
        registrationPage.openPage();
        registrationPage.fillingRegistrationForm("ninja", "ninja@yandex.ru", "ninja123");
        registrationPage.registrationButtonClick();
        registrationPage.getRedirectionToLoginPage();
    }
    @Test
    @DisplayName("Registration user with incorrect password")
    @Description("Test for registration user with incorrect password less then 6 symbols")
    public void registrationUserWithIncorrectPassword() {
        registrationPage.openPage();
        registrationPage.fillingRegistrationForm("ninja", "ninja@yandex.ru", "ajnin");
        registrationPage.registrationButtonClick();
        registrationPage.getIncorrectPasswordError();
    }
    @After
    public void closeDriverSession() {
        try {
            StellarBurgerAPIService stellarBurgerAPIService = new StellarBurgerAPIService();
            stellarBurgerAPIService.deleteUserResponse(stellarBurgerAPIService.loginUserResponse().as(LoginUserDeserialization.class).getAccessToken());
        } catch (RuntimeException r){
            System.out.println("User not created in" + " " + testName.getMethodName() + " method");
        }
        driver.quit();
    }
}
