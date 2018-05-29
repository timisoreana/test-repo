import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;

public class Checkproductpage {
    public String baseUrl = "http://localhost/litecart/en/";
    String driverPath = "F://selenium java//geckodriver//geckodriver.exe";
    private WebDriver driver;


    @BeforeTest
    public void launchBrowser() {
        System.setProperty("webdriver.gecko.driver", driverPath);
        driver = new FirefoxDriver();
        driver.get(baseUrl);

    }
    @Test
    public void checkproductpages() {

        //Attributes form Homepage
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String nameonhp  = driver.findElement(By.cssSelector("#box-campaigns .name")).getAttribute("textContent");
        String regularpriceonhp = driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getAttribute("textContent");
        String regularpricecolorhp = driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getCssValue("color");
        String regularpricetaghp = driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getTagName();
        String regulapricefontsizehp = driver.findElement(By.cssSelector("#box-campaigns .regular-price")).getCssValue("font-size");
        String campaignpriceonhp = driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getAttribute("textContent");
        String campaignpricecolorhp = driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getCssValue("color");
        String campaignpricetaghp = driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getTagName();
        String campaignpricefontsizehp = driver.findElement(By.cssSelector("#box-campaigns .campaign-price")).getCssValue("font-size");

        driver.findElement(By.cssSelector("#box-campaigns .link")).click();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Atributes fromProduct page
        String nameonpp  = driver.findElement(By.cssSelector("h1")).getAttribute("textContent");
        String regularpriceonpp = driver.findElement(By.cssSelector("#box-product .regular-price")).getAttribute("textContent");
        String regularpricecolorpp = driver.findElement(By.cssSelector("#box-product .regular-price")).getCssValue("color");
        String regularpricetagpp = driver.findElement(By.cssSelector("#box-product .regular-price")).getTagName();
        String regulapricefontsizepp = driver.findElement(By.cssSelector("#box-product .regular-price")).getCssValue("font-size");
        String campaignpriceonpp = driver.findElement(By.cssSelector("#box-product .campaign-price")).getAttribute("textContent");
        String campaignpricecolorpp = driver.findElement(By.cssSelector("#box-product .campaign-price")).getCssValue("color");
        String campaignpricetagpp = driver.findElement(By.cssSelector("#box-product .campaign-price")).getTagName();
        String campaignpricefontsizepp = driver.findElement(By.cssSelector("#box-product .campaign-price")).getCssValue("font-size");



        Assert.assertTrue(nameonhp.equals(nameonpp));
        Assert.assertTrue(regularpriceonhp.equals(regularpriceonpp));
        Assert.assertTrue(campaignpriceonhp.equals(campaignpriceonpp));

        //Homepage
        Assert.assertTrue(regularpricecolorhp.equals("rgb(119, 119, 119)"));
        Assert.assertTrue(regularpricetaghp.equals("s"));
        Assert.assertTrue(campaignpricecolorhp.equals("rgb(204, 0, 0)"));
        Assert.assertTrue(campaignpricetaghp.equals("strong"));
        //Product page
        Assert.assertTrue(regularpricecolorpp.equals("rgb(102, 102, 102)"));
        Assert.assertTrue(regularpricetagpp.equals("s"));
        Assert.assertTrue(campaignpricecolorpp.equals("rgb(204, 0, 0)"));
        Assert.assertTrue(campaignpricetagpp.equals("strong"));

        Assert.assertTrue(Double.parseDouble(regulapricefontsizehp.replace("px","")) < Double.parseDouble(campaignpricefontsizehp.replace("px","")));
        Assert.assertTrue(Double.parseDouble(regulapricefontsizepp.replace("px","")) < Double.parseDouble(campaignpricefontsizepp.replace("px","")));



    }

    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }

}
