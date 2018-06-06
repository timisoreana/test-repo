import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.openqa.selenium.WebElement;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Set;
import org.openqa.selenium.support.ui.ExpectedCondition;


public class OpenNewWindow {
    public String baseUrl = "http://localhost/litecart/admin/?app=countries&doc=countries";
    String driverPath = "F://selenium java//geckodriver//geckodriver.exe";
    private WebDriver driver;
    private WebDriverWait wait;



    public ExpectedCondition<String> anyWindowOtherThan(final Set<String> oldWindows ){
        return new ExpectedCondition<String>(){
            public String apply(WebDriver driver){
                Set<String>handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
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
    public void checkOpenNewWindow() {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        driver.findElement(By.cssSelector("#content .button")).click();
        List<WebElement> links = driver.findElements(By.cssSelector("form table tr [target=_blank]"));
        System.out.println(links.size());
        for (WebElement link : links)
         {
            String mainWindow = driver.getWindowHandle();
            Set<String> oldWindows = driver.getWindowHandles();
            link.click();
            String newWindow = wait.until(anyWindowOtherThan(oldWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(mainWindow);
         }
    }
    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }
    }
