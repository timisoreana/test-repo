import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;
import java.lang.System;

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
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        String nameonhp = driver.findElement(By.cssSelector("#box-campaigns .name")).getAttribute("textContent");
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
        String nameonpp = driver.findElement(By.cssSelector("h1")).getAttribute("textContent");
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
        String campaignpricecolorhp1 = campaignpricecolorhp.replace("rgb(", "");
        String campaignpricecolorhp2 = campaignpricecolorhp1.replace(")", "");
        String[] subStrcamphp;
        String delimeter = ", ";
        subStrcamphp = campaignpricecolorhp2.split(delimeter);
        Assert.assertTrue(subStrcamphp[1].equals(subStrcamphp[2]));
        Assert.assertEquals(subStrcamphp[1], "0");

        String regularpricecolorhp1 = regularpricecolorhp.replace("rgb(", "");
        String regularpricecolorhp2 = regularpricecolorhp1.replace(")", "");
        String[] subStrregularhp;
        subStrregularhp = regularpricecolorhp2.split(delimeter);
        Assert.assertTrue(subStrregularhp[0].equals(subStrregularhp[1]));
        Assert.assertTrue(subStrregularhp[1].equals(subStrregularhp[2]));

        Assert.assertTrue(regularpricetaghp.equals("s"));
        Assert.assertTrue(campaignpricetaghp.equals("strong"));
        //Product page
        String campaignpricecolorpp1 = campaignpricecolorpp.replace("rgb(", "");
        String campaignpricecolorpp2 = campaignpricecolorpp1.replace(")", "");
        String[] subStrcamppp;
        subStrcamppp = campaignpricecolorpp2.split(delimeter);
        Assert.assertTrue(subStrcamppp[1].equals(subStrcamppp[2]));
        Assert.assertEquals(subStrcamppp[1], "0");

        String regularpricecolorpp1 = regularpricecolorpp.replace("rgb(", "");
        String regularpricecolorpp2 = regularpricecolorpp1.replace(")", "");
        String[] subStrregularpp;
        subStrregularpp = regularpricecolorpp2.split(delimeter);
        Assert.assertTrue(subStrregularpp[0].equals(subStrregularpp[1]));
        Assert.assertTrue(subStrregularpp[1].equals(subStrregularpp[2]));
        Assert.assertTrue(regularpricetagpp.equals("s"));
        Assert.assertTrue(campaignpricetagpp.equals("strong"));

        Assert.assertTrue(Double.parseDouble(regulapricefontsizehp.replace("px", "")) < Double.parseDouble(campaignpricefontsizehp.replace("px", "")));
        Assert.assertTrue(Double.parseDouble(regulapricefontsizepp.replace("px", "")) < Double.parseDouble(campaignpricefontsizepp.replace("px", "")));
    }
    @AfterTest
     public void stop(){
        driver.quit();
        driver = null;
    }
}
