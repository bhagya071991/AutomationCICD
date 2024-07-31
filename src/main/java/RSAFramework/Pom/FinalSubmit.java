package RSAFramework.Pom;

import RSAFramework.AbstractComponents.AbstractComponents;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class FinalSubmit extends AbstractComponents {
    WebDriver driver;
    public FinalSubmit(WebDriver driver){
        super(driver);
        this.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css="input[placeholder='Select Country']")
    private WebElement getCntry;

    @FindBy(css="section.ta-results button:nth-child(4)")
    private WebElement selectCntry;

    @FindBy(css=".action__submit")
    private WebElement waitforSubmit;
    /*By Submit = By.cssSelector(".action__submit");*/

    private By results =By.cssSelector(".ta-results");

    public void SelectCntry(String myCountry){

        Actions QlkCntry = new Actions(driver);
        QlkCntry.sendKeys(getCntry, myCountry).build().perform();

        waitforElementToAppear(results);
        selectCntry.click();

    }
    public confirmationPage SubmitOrder() throws InterruptedException {
        JavascriptExecutor srolldown = (JavascriptExecutor)driver;
        srolldown.executeScript("window.scrollBy(0,400)");
        Thread.sleep(5000);
        waitforSubmit.click();

        /*waitforSubmit.click();*/
        return new confirmationPage(driver);
    }
}
