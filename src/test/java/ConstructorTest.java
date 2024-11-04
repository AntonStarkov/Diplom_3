import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.pageobject.MainPage;

public class ConstructorTest {
    private WebDriver driver;
    private static MainPage mainPage;
    @Before
    public void setUp() {
        driver = WebDriverData.getWebDriver();
        mainPage = new MainPage(driver);
    }
    @Test
    @DisplayName("Transition to Sauces")
    @Description("Scroll test to sauces section")
    public void transitionsToSauces(){
        mainPage.openPage();
        mainPage.mainPageSaucesButtonClick();
        mainPage.waitingForScrollToSauces();
    }
    @Test
    @DisplayName("Transition to Buns")
    @Description("Scroll test to buns section")
    public void transitionsToBuns() {
        mainPage.openPage();
        mainPage.mainPageFillingsButtonClick();
        mainPage.waitingForScrollToFillings();
        mainPage.mainPageBunsButtonClick();
        mainPage.waitingForScrollToBuns();
    }
    @Test
    @DisplayName("Transition to Fillings")
    @Description("Scroll test to fillings section")
    public void transitionsToFillings(){
        mainPage.openPage();
        mainPage.mainPageFillingsButtonClick();
        mainPage.waitingForScrollToFillings();
    }
    @After
    public void closeDriverSession() {
        driver.quit();
    }
}
