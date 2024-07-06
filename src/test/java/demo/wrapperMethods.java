package demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class wrapperMethods {

    public WebDriverWait wait;
    public WebDriver driver; 

    public wrapperMethods(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void searchBar(By Locator,String Text) throws InterruptedException{

        WebElement textBox = wait.until(ExpectedConditions.visibilityOfElementLocated(Locator));
        textBox.click();
        textBox.clear();
        Thread.sleep(3000);
        textBox.sendKeys(Text);
        textBox.sendKeys(Keys.ENTER);

    }

    public void clickElement(By locator){
        WebElement element = driver.findElement(locator);
        element.click();
    }


   
    
    
}
