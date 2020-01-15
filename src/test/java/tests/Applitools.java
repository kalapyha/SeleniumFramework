package tests;

import com.applitools.eyes.*;
import org.openqa.selenium.*;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Eyes;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Applitools {

    public static void main(String[] args) {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Initialize the Runner for your test.
        EyesRunner runner = new ClassicRunner();

        // Initialize the eyes SDK
        Eyes eyes = new Eyes(runner);

        //Change the APPLITOOLS_API_KEY API key with yours
        eyes.setApiKey("drltAQKZ23Wh9B2fJIDQDZvj9yGAYLC6lJAA6pb2kAU110");


        eyes.open(driver, "My Test", "Test");

        // Navigate the browser to the "ACME" demo app.
        driver.get("https://applitools.com/helloworld?diff1");

        eyes.checkWindow("Home Window");

        driver.findElement(By.xpath("//button")).click(); // click button

        eyes.checkWindow("After click");

        // End the test.
        eyes.closeAsync();

        // Close the browser.
        driver.quit();

        // If the test was aborted before eyes.close was called, ends the test as
        // aborted.
        eyes.abortIfNotClosed();

        // Wait and collect all test results
        TestResultsSummary allTestResults = runner.getAllTestResults();

        // Print results
        System.out.println(allTestResults);


    }


}
