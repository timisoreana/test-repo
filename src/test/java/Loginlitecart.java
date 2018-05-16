import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

public class Loginlitecart {
    public String baseUrl = "http://localhost/litecart/admin/";
    String driverPath = "F://selenium java//geckodriver//geckodriver.exe";
    public WebDriver driver ;

    @BeforeTest
    public void launchBrowser() {
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
        driver.get(baseUrl);
    }
    @Test
    public void login() {
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }
    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }
}
