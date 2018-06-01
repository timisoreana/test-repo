

import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import java.io.File;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;


public class AddProduct {
    public String baseUrl = "http://localhost/litecart/admin";
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
        driver.findElement(By.cssSelector("#box-apps-menu li:nth-child(2) a")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("#content .button:nth-child(2)")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //complete General
        driver.findElement(By.cssSelector("input[name='status'][value='1']")).click();
        driver.findElement(By.cssSelector("[name='name[en]']")).sendKeys("Special water for your duck");
        driver.findElement(By.cssSelector("[name=code]")).sendKeys("water1");
        driver.findElement(By.cssSelector("[name='categories[]'][value='0']")).click();
        driver.findElement(By.cssSelector("[name='categories[]'][value='1']")).click();
        driver.findElement(By.cssSelector("[name='product_groups[]'][value='1-3']")).click();
        driver.findElement(By.cssSelector("[name = 'quantity']")).clear();
        driver.findElement(By.cssSelector("[name = 'quantity']")).sendKeys("50");
        driver.findElement(By.cssSelector("[name='new_images[]']")).sendKeys(new File("src/test/java/apa.png").getAbsolutePath());
        driver.findElement(By.cssSelector("[name='date_valid_from']")).sendKeys("01/07/2018");
        driver.findElement(By.cssSelector("[name='date_valid_to']")).sendKeys("01/07/2019");

        // Go to Information
        driver.findElement(By.cssSelector(".index li:nth-child(2)")).click();

        new Select(driver.findElement(By.cssSelector("[name='manufacturer_id']"))).selectByVisibleText("ACME Corp.");
        driver.findElement(By.cssSelector("[name='keywords']")).sendKeys("diver");
        driver.findElement(By.cssSelector("[name='short_description[en]'")).sendKeys("for my test");
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys("The better water for your duck");
        driver.findElement(By.cssSelector("[name='head_title[en]'")).sendKeys("Water");
        driver.findElement(By.cssSelector("[name='meta_description[en]'")).sendKeys("some description");

        //Go to Price
        driver.findElement(By.cssSelector(".index li:nth-child(4)")).click();
        driver.findElement(By.cssSelector("[name = 'purchase_price']")).clear();
        driver.findElement(By.cssSelector("[name = 'purchase_price']")).sendKeys("35");

        new Select(driver.findElement(By.cssSelector("[name='purchase_price_currency_code']"))).selectByValue("EUR");
        driver.findElement(By.cssSelector("[name = 'prices[USD]']")).clear();
        driver.findElement(By.cssSelector("[name = 'prices[USD]']")).sendKeys("40");
        driver.findElement(By.cssSelector("[name = save]")).click();
        System.out.println("Save a product");


    }
    @AfterTest
    public void stop(){
            driver.quit();
            driver = null;
    }
}

