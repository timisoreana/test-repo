import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;


public class RegisterUser {
    public String baseUrl = "http://localhost/litecart/";
    String driverPath = "F://selenium java//geckodriver//geckodriver.exe";
    private WebDriver driver;


    @BeforeTest
   public void launchBrowser() {
       System.setProperty("webdriver.gecko.driver", driverPath);
       driver = new FirefoxDriver();
      driver.get(baseUrl);
    }
    @Test
    public void register() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("#box-account-login td a")).click();
        String firstName = "Bart";
        String lastName = "Simpson";
        String address = "5th Avenue 5";
        String postcode = "12345";
        String city = "New York";
        String country = "United States";
        String email = "litecart" + Math.floor(Math.random() * 1000) + "@yopmail.com";
        String phone = "1234567";
        String password = "passpass0";

        WebElement newaccount = driver.findElement(By.cssSelector("#create-account .content"));
        newaccount.findElement(By.cssSelector("[name=firstname]")).sendKeys(firstName);
        newaccount.findElement(By.cssSelector("[name=lastname]")).sendKeys(lastName);
        newaccount.findElement(By.cssSelector("[name=address1]")).sendKeys(address);
        newaccount.findElement(By.cssSelector("[name=postcode]")).sendKeys(postcode);
        newaccount.findElement(By.cssSelector("[name=city]")).sendKeys(city);
        newaccount.findElement(By.cssSelector("[name=country_code]")).sendKeys(country);
        newaccount.findElement(By.cssSelector("[name=email]")).sendKeys(email);
        newaccount.findElement(By.cssSelector("[name=phone]")).sendKeys(phone);
        newaccount.findElement(By.cssSelector("[name=password]")).sendKeys(password);
        newaccount.findElement(By.cssSelector("[name=confirmed_password]")).sendKeys(password);
        newaccount.findElement(By.cssSelector("button")).click();


        driver.findElement(By.cssSelector(".account li:nth-child(5) a")).click();

        driver.findElement(By.cssSelector("#box-account-login td input:nth-child(3)")).sendKeys(email);
        driver.findElement(By.cssSelector("#box-account-login td input:nth-child(2)")).sendKeys(password);
        driver.findElement(By.cssSelector("#box-account-login td .button-set [name='login']")).click();

    }
    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }
}
