package RSAFramework.Tests;

import RSAFramework.Pom.*;
import RSAFramework.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class submitOrderTest extends BaseTest {

    @Test
    public  void PlaceOrder() throws IOException, InterruptedException {

        String myItem = "IPHONE 13 PRO";
        String myCountry = "united";

        /*Login HomePage*/
        /*LoginPage loginPage= launchApplication(); Alternative of this s declaring as public method in BaseTest page*/
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
        String expectedText = "THANK YOU FOR THE ORDER.";
        Assert.assertTrue(getTxt.equalsIgnoreCa se(expectedText));

    }

    @Test(dependsOnMethods = {"PlaceOrder"})
    public void OrderHistoryTest(){


    }
}
