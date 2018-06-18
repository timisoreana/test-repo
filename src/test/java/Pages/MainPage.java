package Pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class MainPage extends Page
{
    public MainPage(WebDriver driver) {
        super(driver);
    }

    public MainPage open()
    {
        driver.get("http://localhost/litecart/");
        return this;
    }

    public ProductPage chooseProduct()
    {
        driver.findElement(By.cssSelector("#box-most-popular li:nth-child(1)")).click();
        return new ProductPage(driver);
    }

}