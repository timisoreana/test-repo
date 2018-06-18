import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import org.testng.Assert;
import Pages.CheckoutPage;
import Pages.MainPage;
import Pages.ProductPage;

public class PO_AddToCart {
    public String baseUrl = "http://localhost/litecart/en/";
    String driverPath = "F://selenium java//geckodriver//geckodriver.exe";
    public WebDriver driver;
    public WebDriverWait wait;


    @BeforeTest
    public void launchBrowser()
    {
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
        driver.get(baseUrl);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void test()
    {
        MainPage shopping  = new MainPage(driver);
        ProductPage productpage = new ProductPage(driver);
        CheckoutPage checkoutpage = new CheckoutPage(driver);


        for (int i = 1; i <= 3; i++)
        {
            shopping.open();
            productpage = shopping.chooseProduct();
            productpage.addtocart();
        }

        checkoutpage = productpage.gotoCheckout();
        checkoutpage.removefromCart();

        Assert.assertTrue(checkoutpage.emptyCart());

    }
    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }
}
