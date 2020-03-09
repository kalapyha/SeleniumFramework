package tests;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.VLP;

import java.util.List;


@Listeners(listeners.TestNGListeners.class)

public class ContactForm {
    private WebDriver driver = null;
    private ExtentHtmlReporter html;
    private ExtentReports extent;
    private ExtentTest test = null;
    private VLP inventoryVLP = null;
    private WebDriverWait wait = null;
    private String yearSearch = "2016";
    private String sbm = "InfoRequest-Submit";
    @BeforeSuite
    public void setUpSuit() {

        html = new ExtentHtmlReporter("VLP.html"); //Create HTML report page
        extent = new ExtentReports(); // init report
        extent.attachReporter(html); // attaching report to html page

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // driver init

    }

    @BeforeTest
    public void setTest() {

        driver.get("https://www.openroadsautosales.ca/contact/");
        driver.manage().window().maximize();

    }


@Test
public void checkPageTitleContainsUsedCars() {
   // By contact_form = By.id("iConactForm");

    WebElement contactForm = driver.findElement(By.id(sbm));


//Assert.assertTrue(driver.isVisibleInViewport(contactForm));
}






    //After test
    @AfterTest
    public void testExit(){
        driver.close();
        driver.quit();
    }

    @AfterSuite
    public void tearDown() {
        extent.flush();


    }



}
