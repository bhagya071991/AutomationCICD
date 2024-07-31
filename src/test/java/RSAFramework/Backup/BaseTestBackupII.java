/*Backup  for the Refactored SubmitOrder File or code*/
package RSAFramework.Backup;

import RSAFramework.Pom.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class BaseTestBackupII {

    public static void main(String[] args) throws InterruptedException {

        String myItem = "IPHONE 13 PRO";
        String myCountry = "united";
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();

        /*Login HomePage*/

        LoginPage loginPage = new LoginPage(driver);
        loginPage.gotoURL();

        ProductCatalog productCatalog = loginPage.getCredentials("preeth0789@gmail.com", "Garuda@2024");

        /*Menu Screen select the Menus and add to cart*/

        /*  ProductCatalog productCatalog = new ProductCatalog(driver);*/
        List<WebElement> getAllPrdts = productCatalog.getProduct();

        //get my Menu and add it to cart
        productCatalog.addProductToCart(myItem);
        //Navigate to Cart
        /*PlaceOrder placeOrder= new PlaceOrder(driver);Encapsulate this object creation inside the goToCart method only */

        PlaceOrder placeOrder = productCatalog.goToCart();

        //Get your item from the cart  and checkout
        Boolean match = placeOrder.VerifyChkdprdt(myItem);
        Assert.assertTrue(match);
        FinalSubmit finalSubmit = placeOrder.cartCheckOut();

        finalSubmit.SelectCntry(myCountry);
        finalSubmit.SubmitOrder();

        //Order Confirmation page
        /*Note : equalsIgnoreCase method is deprecated but can be used allong with the String values*/
        confirmationPage confirmationPage = finalSubmit.SubmitOrder();
        String getTxt = confirmationPage.confirmMsg();
        String expectedText = "THANKYOU FOR THE ORDER.";
        Assert.assertTrue(expectedText.equalsIgnoreCase(getTxt));

    }
}
