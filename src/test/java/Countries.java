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

public class Countries {
    public String baseUrl = "http://localhost/litecart/admin/?app=countries&doc=countries";
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
    public void checkcountriesandzones() {

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        List<WebElement> elements = driver.findElements(By.cssSelector(".dataTable .row"));
        List<String> countries = new ArrayList<String>();
        List<String>  countriesdifferentzone = new ArrayList<String>();
             for (WebElement elem : elements)
                  {
                      countries.add(elem.findElement(By.cssSelector("td:nth-child(5) a")).getAttribute("textContent"));
                     if (!(elem.findElement(By.cssSelector("td:nth-child(6)")).getAttribute("textContent")).equals("0"))
                           {
                               countriesdifferentzone.add(elem.findElement(By.cssSelector("a")).getAttribute("textContent"));
                           }
                  }
        List<String> sortedcountries = countries;
        Collections.sort(countries);
        Assert.assertEquals(sortedcountries,countries);
        System.out.println("Countries are alphabetically sorted");
        for (String c : countriesdifferentzone)
            {
                driver.findElement(By.linkText(c)).click();
                elements = driver.findElements(By.cssSelector(".dataTable tr td:nth-child(3)"));
                List<String> zones = new ArrayList<String>();
                for (WebElement z: elements)
                      {
                          zones.add(z.getAttribute("textContent"));
                      }
                sortedcountries = zones;
                Collections.sort(zones);
               Assert.assertEquals(sortedcountries,zones);
                driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
            }
             System.out.println("Zones are alphabetically sorted");
    }
    @AfterTest
    public void stop(){
      driver.quit();
      driver = null;
    }
}

