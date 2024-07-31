package RSAFramework.Pom;

import RSAFramework.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PlaceOrder extends AbstractComponents {
    WebDriver driver;
    public PlaceOrder(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="div.cartSection h3")
    List<WebElement> getCartprdts;

    @FindBy(css="li.totalRow button.btn-primary")
    WebElement chkOut;

    public Boolean VerifyChkdprdt(String myItem){
        return getCartprdts.stream().anyMatch(mycartItem -> mycartItem.getText().equalsIgnoreCase(myItem));
    }

    public FinalSubmit cartCheckOut(){
        chkOut.click();
        return new FinalSubmit(driver);

    }


}
