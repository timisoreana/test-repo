import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;
import java.io.File;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import java.util.concurrent.TimeUnit;


public class AddToCart {
    public String baseUrl = "http://localhost/litecart/en/";
    String driverPath = "F://selenium java//geckodriver//geckodriver.exe";
    private WebDriver driver;
    private WebDriverWait wait;




    @BeforeTest
    public void launchBrowser() {
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
        driver.get(baseUrl);
        wait = new WebDriverWait(driver, 10);

    }

    @Test
    public void addproduct() {
        for (int i = 1; i <= 3; i++) {
            String num = String.valueOf(i);
            driver.get(baseUrl);
            driver.findElement(By.cssSelector("#box-most-popular li:nth-child(1)")).click();
            if (driver.findElement(By.tagName("h1")).getAttribute("textContent").equals("Yellow Duck"))
                new Select(driver.findElement(By.cssSelector("[name='options[Size]']"))).selectByValue("Small");
            driver.findElement(By.cssSelector(".quantity button")).click();
            wait.until(textToBePresentInElementLocated(By.cssSelector("#cart .quantity"),num));
        }
        driver.findElement(By.cssSelector("#cart .link")).click();
        List<WebElement> cartlist = driver.findElements(By.cssSelector("#order_confirmation-wrapper tr td.item"));
        for (int i = 0; i < cartlist.size()  ; i++) {
            List<WebElement> shortcuts = driver.findElements(By.cssSelector("#box-checkout-cart .shortcuts li"));
            if (shortcuts.size() > 0) {
                WebElement shortcut = driver.findElement(By.cssSelector("#box-checkout-cart .shortcuts li:nth-child(1)"));
                shortcut.click();
                WebElement btn = wait.until(elementToBeClickable(By.cssSelector("[name='remove_cart_item']")));
                btn.click();
                wait.until(stalenessOf(cartlist.get(0)));
            } else {
                WebElement btn = wait.until(elementToBeClickable(By.cssSelector("[name='remove_cart_item']")));
                btn.click();
                wait.until(stalenessOf(cartlist.get(0)));
            }
        }
        wait.until(numberOfElementsToBe(By.cssSelector("#checkout-cart-wrapper p"), 2));
        Assert.assertEquals(driver.findElement(By.cssSelector("#checkout-cart-wrapper p")).getText(),
                "There are no items in your cart.");

    }
    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }
}
