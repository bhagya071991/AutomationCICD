package RSAFramework.Pom;

import RSAFramework.AbstractComponents.AbstractComponents;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractComponents {

    WebDriver driver;

    /*Below is the constructor and when you are calling the arguments you need to define the webdriver here as well
     * Somebody will call your class with one variable , The other class StandaloneTest file driver will sit here in
     * LandingPage(WebDriver driver) and from here we are sending the details to  this.driver =driver were this.driver will act
     * as reference to the Local Driver class */

    //The below driver is coming from BaseTest or TestCases
    //Super will use this variable or driver of the child

    public LoginPage(WebDriver driver) {

        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void gotoURL(){
        driver.get("https://rahulshettyacademy.com/client");
    }
    //PageFactory Introduction using FindBy

    @FindBy(css = "input#userEmail")
    WebElement UserName;
    @FindBy(css="input#userPassword")
    WebElement Pswd;
    @FindBy(css="input#login")
    WebElement submitBtn;
    @FindBy(css="[class*='flyInOut']")
    WebElement exceptions;

    //Below is the Action Methods for the above webelement
   public ProductCatalog getCredentials(String uname, String upswd){

       UserName.sendKeys(uname);
       Pswd.sendKeys(upswd);
       submitBtn.click();

       //Creating the object inside for the next class to avoid overhead
       return new ProductCatalog(driver);
   }
   public String getExceptionmsg(){
       waitforWebElementToAppear(exceptions);
       return exceptions.getText();

   }


}

