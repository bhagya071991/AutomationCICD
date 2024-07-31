package RSAFramework.Tests;

import RSAFramework.Pom.PlaceOrder;
import RSAFramework.Pom.ProductCatalog;
import RSAFramework.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class exceptionHandlingTest extends BaseTest {

    public  ProductCatalog productCatalog;
    @Test
    public  void ExceptionHandle() throws IOException, InterruptedException {

        String myItem = "IPHONE 13 PRO";
        String myCountry = "united";

        /*Login HomePage*/
        /*LoginPage loginPage= launchApplication(); Alternative of this s declaring as public method in BaseTest page*/
        productCatalog = loginPage.getCredentials("rahulshetty@gmail.com", "Garud2024");
        loginPage.getExceptionmsg();
        Assert.assertEquals("Incorrect email or password.",loginPage.getExceptionmsg());

    }

    @Test
    public void productException() throws InterruptedException {

        String myItem = "IPHONE 13 PRO";
        List<WebElement> getAllPrdts = productCatalog.getProduct();
        productCatalog.addProductToCart(myItem);
        PlaceOrder placeOrder = productCatalog.goToCart();

        //Get your item from the cart  and checkout
        Boolean match = placeOrder.VerifyChkdprdt(myItem);
        Assert.assertTrue(match);
    }
}
