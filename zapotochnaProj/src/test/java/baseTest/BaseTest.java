package baseTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pages.HomePage;
import pages.LoginPage;

import java.time.Duration;

public class BaseTest { //батьківський клас для всіх класів, все що відноситься до тестів
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    protected LoginPage loginPage;
    protected HomePage homePage;

    @Before //pre-cond
    public void setUp() {
        logger.info("-------- " + testName.getMethodName() + " --------");

        webDriver = initDriver();

        webDriver.manage().window().maximize();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        loginPage = new LoginPage(webDriver);


        homePage = new HomePage(webDriver);

    }


    @After
    public void tearDown() {
        webDriver.quit();
        logger.info("browser closed");

        logger.info("test is ended " + testName.getMethodName() + " --------");

    }

    @Rule // ця анотація запускається незалежно. перед раном тесту, запише в змінну ім'я тесту який зараз запускається
    public TestName testName = new TestName();

    private WebDriver initDriver() {

        String browser = System.getProperty("browser"); // передаємо цю зміну при запуску тесту  , якщо не передаємо по-дефолту буде null
        if ((browser == null) || "chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();
            webDriver = new ChromeDriver();
        } else if ("firefox".equalsIgnoreCase(browser)) {
            WebDriverManager.firefoxdriver().setup();
            webDriver = new FirefoxDriver();

        } else if ("edge".equalsIgnoreCase(browser)) {
            WebDriverManager.edgedriver().setup();
            webDriver = new EdgeDriver();
        }
//        else if ("ie".equalsIgnoreCase(browser)) {
//            //WebDriverManager.iedriver().setup();
//            // in most cases 32bit version is needed
//            WebDriverManager.iedriver().arch32().setup();
//            return new InternetExplorerDriver();
//        }
        return webDriver;
    }
}
