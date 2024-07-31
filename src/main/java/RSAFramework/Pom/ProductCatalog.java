package RSAFramework.Pom;

import RSAFramework.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalog extends AbstractComponents {

    WebDriver driver;

    public ProductCatalog(WebDriver driver){

        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    /*Menu Screen select the Menus and add to cart*/
    @FindBy(css="div.mb-3")
    List<WebElement> getAllprd;

    By products = By.cssSelector("div.mb-3");
    By addprdToCart= By.cssSelector("div.card-body button:last-of-type");
    By loadPage =By.cssSelector("#toast-container");
    By  loading = By.cssSelector(".ng-animating");

    public List<WebElement>  getProduct(){
        //get my Menu and add it to cart
        waitforElementToAppear(products);
        return getAllprd;
    }
    //Wait for get the menu
    public WebElement getMenuName(String myItem){
        WebElement getMyprd = getAllprd.stream().filter(menus ->menus.findElement(By.cssSelector("b"))
                .getText().equalsIgnoreCase(myItem)).findFirst().orElse(null);
        return getMyprd;

    }
    //Wait for Loading page to disappear after the item added to cart
    public void addProductToCart(String myItem) throws InterruptedException {
        WebElement addToCart = getMenuName(myItem);
        addToCart.findElement(addprdToCart).click();
        waitforElementToAppear(loadPage);
        waitforElementToDisappear(loading);

    }

}
