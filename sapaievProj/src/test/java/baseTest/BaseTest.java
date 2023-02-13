package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.apache.log4j.Logger;
import pages.HomePage;
import pages.LoginPage;
import pages.MyProfilePage;
import pages.elements.HeaderElement;

import java.time.Duration;


public class BaseTest {
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected HeaderElement headerElement;

    protected MyProfilePage myProfilePage;




    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        webDriver = new ChromeDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        loginPage = new LoginPage(webDriver);
        homePage=new HomePage(webDriver);
        myProfilePage=new MyProfilePage(webDriver);


    }


    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("Browser was closed");
    }


}
