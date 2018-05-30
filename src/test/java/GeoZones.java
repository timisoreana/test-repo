import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import java.util.*;

public class GeoZones {
    public String baseUrl = "http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones";
    String driverPath = "F://selenium java//geckodriver//geckodriver.exe";
    private WebDriver driver;


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
    public void checkgeozones() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> elements = driver.findElements(By.cssSelector(".dataTable .row td:nth-child(3)"));
        ArrayList<String> countrieslist = new ArrayList<String>();
        for (WebElement el : elements)
        {
            countrieslist.add(el.getAttribute("textContent"));
        }
        for (String c : countrieslist) {
            driver.findElement(By.linkText(c)).click();

            elements = driver.findElements(By.cssSelector(".dataTable tr td:nth-child(3) select option:checked"));
            List<String> geozones = new ArrayList();
            for (WebElement elem : elements) {
                geozones.add(elem.getAttribute("textContent"));
            }
            List<String> copy = geozones;
            Collections.sort(geozones);
            Assert.assertEquals(copy, geozones);
            driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        }
    }
    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }
}
