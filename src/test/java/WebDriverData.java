import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.github.bonigarcia.wdm.WebDriverManager;


public class WebDriverData {
    private static final String VALUE_OF_BROWSER_NAME_ENV = System.getProperty("browser");
    static WebDriver switchWebDriver(Browser browser){
        switch (browser){
            case CHROME:
                var chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                return new ChromeDriver(chromeOptions);
            case YANDEX:
                WebDriverManager.chromedriver().driverVersion(System.getProperty("driver.version")).setup();
                var yandexOptions = new ChromeOptions();
                yandexOptions.setBinary(System.getProperty("webdriver.yandex.bin"));
                yandexOptions.addArguments("start-maximized");
                return new ChromeDriver(yandexOptions);
            default:
                throw new RuntimeException("can't create driver");
        }
    }
    static WebDriver getWebDriver(){
        return switchWebDriver(Browser.valueOf(VALUE_OF_BROWSER_NAME_ENV));
    }
    enum Browser{
        CHROME, YANDEX;
    }
}
