import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;



public class CheckLogs {
    public String baseUrl = "http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1";
    String driverPath = "F://selenium java//chromedriver//chromedriver.exe";
    private WebDriver driver;

    @BeforeTest
    public void launchBrowser() {
        System.setProperty("webdriver.chrome.driver", driverPath);
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @Test
    public void newproduct() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        List<WebElement> mylist = driver.findElements(By.xpath(".//tr/td[3]/a"));
        List<String> myproducts = new ArrayList<String>();
        for (WebElement m : mylist)
        {
            if (!m.getText().equals("Subcategory"))
                myproducts.add(m.getAttribute("textContent"));
        }
        for (String g : myproducts)
        {
            driver.findElement(By.linkText(g)).click();
            System.out.println(driver.manage().logs().get("browser").getAll().size() == 0);
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        }

    }
    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }

}
