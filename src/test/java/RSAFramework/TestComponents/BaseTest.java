package RSAFramework.TestComponents;

import RSAFramework.Pom.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class BaseTest {
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    public WebDriver driver;
    public LoginPage loginPage;
    //here the variable is Null and gets activated once initialized with new loginpage(driver)
    public WebDriver initializeDriver() throws IOException {

        //Using the Properties class and FileInputStream Class
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") +
                "//src//main//java//RSAFramework//Resources//GlobalData.properties");
        prop.load(fis);
        String getBrowser =prop.getProperty("browser");

        if(getBrowser.equalsIgnoreCase("chrome")){

             driver = new ChromeDriver();

        } else if (getBrowser.equalsIgnoreCase("edge")) {
            driver= new EdgeDriver();
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
        driver.manage().window().maximize();
        return driver;
    }
    @BeforeMethod
    public LoginPage launchApplication() throws IOException {
      driver = initializeDriver();
       loginPage= new LoginPage(driver);
        loginPage.gotoURL();
        return loginPage;

    }
    @AfterMethod
    public void tearDown(){
        driver.close();
    }
}
