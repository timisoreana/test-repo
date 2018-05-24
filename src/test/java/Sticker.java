import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.openqa.selenium.By;


public class Sticker {
    public String baseUrl = "http://localhost/litecart/en/";
    String driverPath = "F://selenium java//geckodriver//geckodriver.exe";
    public WebDriver driver ;

    @BeforeTest
    public void launchBrowser() {
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
        driver.get(baseUrl);
    }

    @Test
    public void checkstickers() {
        List<WebElement> elements = driver.findElements(By.cssSelector(".product"));
        for (WebElement elem :elements) {
            Assert.assertTrue(elem.findElements(By.cssSelector(".sticker")).size() == 1);
        }
    }

    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }
}
