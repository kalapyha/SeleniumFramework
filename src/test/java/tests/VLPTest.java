package tests;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.pagefactory.ByChained;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.VLP;

import java.util.List;


@Listeners(listeners.TestNGListeners.class)

public class VLPTest {
    private WebDriver driver = null;
    private ExtentHtmlReporter html;
    private ExtentReports extent;
    private ExtentTest test = null;
    private VLP inventoryVLP = null;
    private WebDriverWait wait = null;
    private String yearSearch = "2016";

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

        driver.get("https://www.openroadsautosales.ca/used/");
        driver.manage().window().maximize();




    }

    @Test()
    public void checkPageTitleContainsUsedCars() {
        System.out.println("Inside test checkPageTitleContainsUsedCars | Thread : " + Thread.currentThread().getId());
        test = extent.createTest("Check VLP title contains 'Cars, SUVs, Trucks for Sale' to make sure we are at the correct page");

        Assert.assertTrue(driver.getTitle().contains("Cars, SUVs, Trucks for Sale"));

        test.pass("navigated to the " + driver.getCurrentUrl() + " successfully");
    }

    @Test
    public void checkLocationUsingTitleAddress() {
        System.out.println("Inside test checkLocationUsingTitleAddress | Thread : " + Thread.currentThread().getId());
        test = extent.createTest("Check if address the same as location");

        inventoryVLP = new VLP(driver); // Initialize instance from objects page

        Assert.assertTrue(driver.getTitle().contains(inventoryVLP.getPartialAddressFromPage()));
        test.pass("Address match location");
    }

    @Test()
    public void inventorySearchUsingEnter() {

        System.out.println("Inside test inventorySearchUsingEnter | Thread : " + Thread.currentThread().getId());
        test = extent.createTest("VLP search inventory test using ENTER key");
        test.pass("navigated to the " + driver.getCurrentUrl() + " successfully");


        inventoryVLP = new VLP(driver); // Initialize instance from objects page
        inventoryVLP.searchText(yearSearch); // Entering 2016 using "yearSearch" variable end press ENTER

        Assert.assertTrue(inventoryVLP.getSearchingYearValue().contains(yearSearch)); // Check if year equals filter year
        test.pass(inventoryVLP.getSearchingYearValue() + " - correct searching request");

        inventoryVLP.clickClearAllButton();// Clear all button clicked

        Assert.assertTrue(inventoryVLP.isVisibleInViewport(inventoryVLP.getClearAllButton()) == false);
        test.pass("Clear All Button is not visible in viewport after closing");

    }

    @Test()
    public void inventorySearchUsingSearchIcon() {

        driver.navigate().refresh(); // Refresh the page to avoid duplicates

        System.out.println("Inside test inventorySearchUsingSearchIcon | Thread : " + Thread.currentThread().getId());
        test = extent.createTest("VLP search inventory test using ENTER key");
        test.pass("navigated to the " + driver.getCurrentUrl() + " successfully");


        inventoryVLP = new VLP(driver); // Initialize instance from objects page

        inventoryVLP.findSearchInputAndInsertTextOnly(yearSearch);  // Entering 2016 using variable
        inventoryVLP.clickSearchButton(); // Click search icon

        Assert.assertTrue(inventoryVLP.getSearchingYearValue().contains(yearSearch)); // Check if year equals filter year
        test.pass(inventoryVLP.getSearchingYearValue() + " - correct searching request");

        inventoryVLP.clickClearAllButton();// Clear all button clicked

        Assert.assertTrue(inventoryVLP.isVisibleInViewport(inventoryVLP.getClearAllButton()) == false);
        test.pass("Clear All Button is not visible in viewport after closing");

    }

    @Test()
    public void inventorySetUsingCheckTotalCars() {

        driver.navigate().refresh(); // Refresh the page to avoid duplicates
        System.out.println("Inside test inventoryCheckTotalCars | Thread : " + Thread.currentThread().getId());
        test = extent.createTest("VLP check if inventory is available using total cars amount");

        inventoryVLP = new VLP(driver); // Initialize instance from objects page

        String carsTotal = inventoryVLP.getCarsAvailableTotal();
        test.pass("Total cars have been parsed");

        Assert.assertTrue(inventoryVLP.getNumbersFromString(carsTotal) > 0);
        test.pass("Total cars amount is greater than zero - inventory set");

    }

    @Test
    public void checkHideMoreOptionsFilter() {
        System.out.println("Inside test checkHideMoreOptionsFilter | Thread : " + Thread.currentThread().getId());
        test = extent.createTest("Check if hide more options filter is responding");

        inventoryVLP = new VLP(driver); // Initialize instance from objects page

        WebElement bottomFilters = inventoryVLP.getBottomSearchFilters();
        Assert.assertTrue(inventoryVLP.isVisibleInViewport(bottomFilters));

        test.pass("Bottom filter is visible");

        inventoryVLP.clickCollapseBottomFilter();
        test.pass("Collapse bottom filter button have been clicked");

        Assert.assertFalse(inventoryVLP.isVisibleInViewport(bottomFilters));
        test.pass("Bottom filter is NOT visible");
    }

    @Test()
    public void checkPriceFilter() {
        System.out.println("Inside test checkPriceFilter | Thread : " + Thread.currentThread().getId());
        test = extent.createTest("Check if price filter is responding and works correct");

        inventoryVLP = new VLP(driver); // Initialize instance from objects page
        int lowPrice = inventoryVLP.getLowestPrice();
        test.pass("lower price detect");
        driver.navigate().refresh();
        int highPrice = inventoryVLP.getHighestPrice();
        test.pass("higher price detect");

        Assert.assertTrue(lowPrice <= highPrice);
        test.pass("Filter works fine: " + lowPrice + " is less than " + highPrice + " !");

    }


    // TODO: 2020-01-09 !!!!!! END OF THE CHECKED TESTS
    @Test(enabled = false) // Test 3
    public void checkFinanceButton() throws Exception{
        System.out.println("Inside test 3 | Thread : " + Thread.currentThread().getId());

        test = extent.createTest("VLP search Financing");

            test.pass("navigated to the " + driver.getCurrentUrl() + " successfully");

            driver.findElement(By.linkText("Apply for Financing")).click();
            //Thread.sleep(5000);

            // driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);
            //driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            WebElement closePopWindow = driver.findElement(By.cssSelector("button.close-apply-for-financing"));

            wait = new WebDriverWait(driver, 30);
            wait.until((ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button.close-apply-for-financing"))));

            System.out.println(closePopWindow.isDisplayed() + " Should be true");
             JavascriptExecutor executor = (JavascriptExecutor)driver;
             executor.executeScript("arguments[0].click();", closePopWindow);
            Thread.sleep(5000); //Wait until pop-up is closed
            System.out.println(closePopWindow.isDisplayed());

             // closePopWindow.click();

    }

    @Test(enabled = false)
    public void checkTest() {

        List<WebElement> testList = driver.findElements(
                new ByChained(By.id("nav-v3-ul-top"), By.id("bodystyleDDL"),
                        By.cssSelector("li.selected-false"),
                        By.cssSelector("label.checkbox"))
        );
        System.out.println(testList.size());
        System.out.println(testList.get(2).getText() + " Inside SOUT");
        System.out.println(driver.findElement(By.cssSelector("span.vehicle-price-2-new > span")).getText());

    }






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
