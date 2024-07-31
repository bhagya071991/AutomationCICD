package RSAFramework.AbstractComponents;

import RSAFramework.Pom.PlaceOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AbstractComponents {

    WebDriver driver;
    //The below constructors catches the child of the LandingPage or the driver,variable sent by
    //child is caught in the constructor
    //Scope of (WebDriver driver) is within the method , in order to interact with the other driver of  methods globally declared
    public AbstractComponents(WebDriver driver){
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }
    public void waitforElementToAppear(By findBy){

        WebDriverWait waitIf = new WebDriverWait(driver, Duration.ofSeconds(5));
        waitIf.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }
    public void waitforWebElementToAppear(WebElement findBy){

        WebDriverWait waitIf = new WebDriverWait(driver, Duration.ofSeconds(5));
        waitIf.until(ExpectedConditions.visibilityOf(findBy));
    }
    public void waitforElementToDisappear(By findBy) throws InterruptedException {

        Thread.sleep(3000);
        WebDriverWait waitIf = new WebDriverWait(driver, Duration.ofSeconds(5));
        waitIf.until(ExpectedConditions.invisibilityOfElementLocated(findBy));
    }
    @FindBy(xpath="//button[@routerlink='/dashboard/cart']")
    WebElement onclickCart;

    //Example of Encapsulation
    public PlaceOrder goToCart(){
        onclickCart.click();
        /*PlaceOrder placeOrder= new PlaceOrder(driver);*/
        return new PlaceOrder(driver);
    }


}
