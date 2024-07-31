package RSAFramework.Backup;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class BaseTestbackup {

  public static void main(String[] args) throws InterruptedException {

    String myItem = "IPHONE 13 PRO";
    WebDriver driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    driver.manage().window().maximize();
    WebDriverWait waitIf = new WebDriverWait(driver,Duration.ofSeconds(5));

    driver.get("https://rahulshettyacademy.com/client");

    /*Login HomePage*/
    WebElement userName = driver.findElement(By.cssSelector("input#userEmail"));
    userName.sendKeys("preeth0789@gmail.com");
    WebElement userPswd =driver.findElement(By.cssSelector("input#userPassword"));
    userPswd.sendKeys("Garuda@2024");
    WebElement submitBtn = driver.findElement(By.cssSelector("input#login"));
    submitBtn.click();

    /*Menu Screen select the Menus and add to cart*/
    waitIf.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.mb-3")));
    List<WebElement> getAllprd = driver.findElements(By.cssSelector("div.mb-3"));

    //get my Menu and add it to cart
    WebElement getMyprd = getAllprd.stream().filter(menus ->menus.findElement(By.cssSelector("b"))
            .getText().equalsIgnoreCase(myItem)).findFirst().orElse(null);

    WebElement addToCart = getMyprd.findElement(By.cssSelector("div.card-body button:last-of-type"));
    addToCart.click();

    //Loading page
    waitIf.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
    WebElement loadingPage = driver.findElement(By.cssSelector(".ng-animating"));
    waitIf.until(ExpectedConditions.invisibilityOf(loadingPage));

    //Navigate to Cart
    WebElement qlickonCart = driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']"));
    qlickonCart.click();

    //Get your item from the cart  and checkout
    List<WebElement> getCartprdts = driver.findElements(By.cssSelector("div.cartSection h3"));
    Boolean getMyprdt = getCartprdts.stream().anyMatch(mycartItem->mycartItem.getText().equalsIgnoreCase(myItem));

    WebElement chkOut = driver.findElement(By.cssSelector("li.totalRow button.btn-primary"));
    chkOut.click();

    //Payment page
    String myCountry ="united";
    WebElement getCntry = driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
    Actions QlkCntry = new Actions(driver);
    QlkCntry.sendKeys(getCntry,myCountry).build().perform();

    WebElement selectCntry = driver.findElement(By.cssSelector("section.ta-results button:nth-child(4)"));
    WebElement waitnClick = waitIf.until(ExpectedConditions.visibilityOf(selectCntry));
    waitnClick.click();

    //Submit
    JavascriptExecutor srolldown = (JavascriptExecutor)driver;
    srolldown.executeScript("window.scrollBy(0,400)");
    Thread.sleep(5000);
    WebElement waitforSubmit = waitIf.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".action__submit")));
    waitforSubmit.click();


    //Order Confirmation page
    /*Note : equalsIgnoreCase method is deprecated but can be used allong with the String values*/
    WebElement orderconfirmTxt = driver.findElement(By.cssSelector(".hero-primary"));
    String getTxt =orderconfirmTxt.getText();

    String expectedText="THANKYOU FOR THE ORDER.";
    Assert.assertTrue(expectedText.equalsIgnoreCase(getTxt));




  }
}
