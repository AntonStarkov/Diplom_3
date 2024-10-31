import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.StellarBurgerAPIService;
import ru.yandex.praktikum.pageobject.AuthPage;
import ru.yandex.praktikum.pageobject.MainPage;
import ru.yandex.praktikum.pageobject.PersonalAccountPage;
import ru.yandex.praktikum.pageobject.RegistrationPage;


public class PersonalAccountTest {
    private WebDriver driver;
    private String userToken;
    private static AuthPage authPage;
    private static PersonalAccountPage personalAccountPage;
    private static MainPage mainPage;
    @Rule
    public TestName testName = new TestName();
    @Before
    public void setUp() throws InterruptedException {
        driver = WebDriverData.getWebDriver();
        mainPage = new MainPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        authPage = new AuthPage(driver);
        personalAccountPage = new PersonalAccountPage(driver);
        registrationPage.openPage();
        registrationPage.fillingRegistrationForm("ninja", "ninja@yandex.ru", "ninja123");
        registrationPage.registrationButtonClick();
        registrationPage.getRedirectionToLoginPage();
        authPage.fillingAuthForm("ninja@yandex.ru", "ninja123");
        authPage.authButtonClick();
        Thread.sleep(1000);
        userToken = ((JavascriptExecutor) driver)
                .executeScript(String.format("return window.localStorage.getItem('accessToken');")).toString();
    }
    @Test
    public void personalAccountPageOpen() throws InterruptedException {
        Thread.sleep(1000);
        new MainPage(driver).mainPagePersonalAccountButtonClick();
        new PersonalAccountPage(driver).waitForExitButton();
    }
    @Test
    public void personalAccountLogout() throws InterruptedException {
        Thread.sleep(1000);
        mainPage.mainPagePersonalAccountButtonClick();
        personalAccountPage.waitForExitButton();
        personalAccountPage.exitButtonClick();
        authPage.personalAccountButtonClick();
        authPage.getRedirectionToLoginPage();
    }
    @Test
    public void fromPersonalAccountToConstructorViaLogo() throws InterruptedException {
        Thread.sleep(1000);
        mainPage.mainPagePersonalAccountButtonClick();
        personalAccountPage.waitForExitButton();
        personalAccountPage.logoButtonClick();
        personalAccountPage.getRedirectionToMainPage();
    }
    @Test
    public void fromPersonalAccountToConstructorViaConstructorButton() throws InterruptedException {
        Thread.sleep(1000);
        mainPage.mainPagePersonalAccountButtonClick();
        personalAccountPage.waitForExitButton();
        personalAccountPage.constructorButtonClick();
        personalAccountPage.getRedirectionToMainPage();
    }
    @After
    public void closeDriverSession() {
        try {
            new StellarBurgerAPIService().deleteUserResponse(userToken);
        } catch (NullPointerException e){
            System.out.println("User not created in" + " " + testName.getMethodName() + " method");
        }
        driver.quit();
    }
}
