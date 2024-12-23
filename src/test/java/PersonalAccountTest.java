import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.StellarBurgerAPIService;
import ru.yandex.praktikum.pageobject.AuthPage;
import ru.yandex.praktikum.pageobject.MainPage;
import ru.yandex.praktikum.pageobject.PersonalAccountPage;
import ru.yandex.praktikum.pojo.createuser.CreateUserDeserialization;


public class PersonalAccountTest {
    private WebDriver driver;
    private static AuthPage authPage;
    private static PersonalAccountPage personalAccountPage;
    private static MainPage mainPage;
    private String accessToken;
    private StellarBurgerAPIService stellarBurgerAPIService;
    @Rule
    public TestName testName = new TestName();
    @Before
    public void setUp() {
        driver = WebDriverData.getWebDriver();
        mainPage = new MainPage(driver);
        authPage = new AuthPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
        stellarBurgerAPIService = new StellarBurgerAPIService();
        accessToken = stellarBurgerAPIService.createUserResponse()
                .as(CreateUserDeserialization.class).getAccessToken();
        authPage.openPage();
        authPage.fillingAuthForm("ninja@yandex.ru", "ninja123");
        authPage.authButtonClick();
    }
    @Test
    @DisplayName("Open personal account page")
    @Description("Test for opening personal account page")
    public void personalAccountPageOpen() {
        new MainPage(driver).mainPagePersonalAccountButtonClick();
        new PersonalAccountPage(driver).waitForExitButton();
    }
    @Test
    @DisplayName("Logout from personal account page")
    @Description("Test for logout user from personal account page")
    public void personalAccountLogout() {
        mainPage.mainPagePersonalAccountButtonClick();
        personalAccountPage.waitForExitButton();
        personalAccountPage.exitButtonClick();
        authPage.personalAccountButtonClick();
        authPage.getRedirectionToLoginPage();
    }
    @Test
    @DisplayName("Jump from personal account page to constructor via logo")
    @Description("Test for transition from personal account page to constructor after click by logo")
    public void fromPersonalAccountToConstructorViaLogo() {
        mainPage.mainPagePersonalAccountButtonClick();
        personalAccountPage.waitForExitButton();
        personalAccountPage.logoButtonClick();
        personalAccountPage.getRedirectionToMainPage();
    }
    @Test
    @DisplayName("Jump from personal account page to constructor via constructor button")
    @Description("Test for transition from personal account page to constructor after click by constructor button")
    public void fromPersonalAccountToConstructorViaConstructorButton() {
        mainPage.mainPagePersonalAccountButtonClick();
        personalAccountPage.waitForExitButton();
        personalAccountPage.constructorButtonClick();
        personalAccountPage.getRedirectionToMainPage();
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
