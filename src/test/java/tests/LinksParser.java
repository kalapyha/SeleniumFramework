package tests;

import org.openqa.selenium.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class LinksParser {
    public static void main(String[] args) {
        // WebDriverManager.chromedriver().setup();
        System.setProperty("webdriver.chrome.driver", "/Users/mykola/Desktop/SeleniumFramework-master/drivers/chrome/chromedriver");

        //WebDriver driver = new ChromeDriver();
        WebDriver driver = new ChromeDriver();

        String homePage = "https://www.parkwaylincoln.ca/";
        String url = "";

        driver.get(homePage);
        driver.manage().window().maximize();

        List<WebElement> links = driver.findElements(By.tagName("a"));
        List<String> validLinks = new ArrayList<String>();
        List<String> v3Links = new ArrayList<String>();


        Iterator<WebElement> it = links.iterator();

        while(it.hasNext()) {

            url = it.next().getAttribute("href");

            System.out.println(url);

            if (url == null || url.isEmpty()) {
                System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }

            if (!url.startsWith(homePage)) {
                System.out.println("URL belongs to another domain, skipping it.");
                continue;
            }
            validLinks.add(url);
        }

        List<Object> duplicatesFreeLinks = validLinks.stream().distinct().collect(Collectors.toList());
        Iterator<Object> iter = duplicatesFreeLinks.iterator();

        while(iter.hasNext()) {
            driver.get((String) iter.next());
            System.out.println(" v3 Page? ");
            Boolean isPresent = driver.findElements(By.tagName("iframe")).size() > 0;
            if(isPresent) {
                String myUrl = driver.getCurrentUrl().toString();
                v3Links.add(myUrl);
                System.out.println("Element present");
            } else {
                System.out.println("Element not present");
            }
        }



        System.out.println(validLinks);
        System.out.println(validLinks.size());

        System.out.println(duplicatesFreeLinks);
        System.out.println(duplicatesFreeLinks.size());

        System.out.println(v3Links);
        System.out.println(v3Links.size());

        driver.close();
        driver.quit();

    }
}
