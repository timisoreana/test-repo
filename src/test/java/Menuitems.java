import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;


public class Menuitems {
    public String baseUrl = "http://localhost/litecart/admin/";
    String driverPath = "F://selenium java//geckodriver//geckodriver.exe";
    List items = new ArrayList();
    private WebDriver driver;
    private boolean areElementsPresent(WebDriver driver, By locator)
    {
        return driver.findElements(locator).size() > 0;
    }

    @BeforeTest
    public void launchBrowser() {
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
        driver.get(baseUrl);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void checkpages() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> items = driver.findElements(By.id("app-")); //(By.cssSelector("li#app-"));
        System.out.println("Total items are " + items.size());
        for(int j=1; j <= items.size(); j++) {

            driver.findElement(By.cssSelector("li#app-:nth-child("+ j +") a")).click();
            System.out.println("Click on "+ j + " item");
            driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
            Assert.assertTrue(areElementsPresent(driver,By.cssSelector("h1")));
            System.out.println("Checked h1 on "+ j + " item");
            List<WebElement> childitems = driver.findElements(By.cssSelector("li[id^=doc]"));
            if (childitems.size() > 0){
                for (int k = 2; k <= childitems.size(); k++){
                    driver.findElement(By.cssSelector("li[id^=doc]:nth-child("+ k +") a")).click();
                    System.out.println("Click on "+ k + " childitem "+ j + "item");
                    Assert.assertTrue(areElementsPresent(driver,By.cssSelector("h1")));
                    System.out.println("Checked h1 on "+ k + " childitem "+ j + "item");
                }
            }
        }

    }

    @AfterTest
    public void stop(){
       driver.quit();
       driver = null;
    }
}
