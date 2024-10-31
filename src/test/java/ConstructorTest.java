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
    public void transitionsToSauces(){
        mainPage.openPage();
        mainPage.mainPageSaucesButtonClick();
        mainPage.waitingForScrollToSauces();
    }
    @Test
    public void transitionsToBuns() throws InterruptedException {
        mainPage.openPage();
        mainPage.mainPageSaucesButtonClick();
        Thread.sleep(3000);
        mainPage.mainPageBunsButtonClick();
        mainPage.waitingForScrollToBuns();
    }
    @Test
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
