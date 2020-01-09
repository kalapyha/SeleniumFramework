package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.List;

public class VLP {

    WebDriver driver = null;

    By textbox_search = By.id("search_keywords");
    By button_search = By.id("current-criteria");
    By button_clear_all = By.cssSelector("div.clear-all-filters > a");
    By bottom_filter_area = By.cssSelector("div.v3-filter-bottom");
    By collapse_bottom_filter = By.cssSelector("div.nav-v3-more > p");
    By address_location = By.id("new-listing-title");
    By sort_by_filter = By.cssSelector("button.btn-sort-by-v31.dropdown-toggle.mobile-sort-filter1.stat-dropdown");
    By vehicle_price = By.cssSelector("span.vehicle-price-2-new > span");
    By sort_price_asc = By.cssSelector("#sortPriceAsc > a");
    By sort_price_desc = By.cssSelector("#sortPriceDesc > a");
    By compare_checkbox = By.cssSelector("div.checkbox.checkbox-btn.select-trim-mobile.listing-comparison-checkbox.compare-checkbox-btn");
    By switch_grid_view = By.id("switchGridView");
    By switch_table_view = By.id("switchTableView");

    // ------------  CONSTRUCTOR WITH DRIVER  ------------
    public VLP(WebDriver driver) {

        this.driver = driver;

    }

    public WebElement getClearAllButton() {
        return driver.findElement(button_clear_all);
    }

    public WebElement getBottomSearchFilters() {
        return driver.findElement(bottom_filter_area);
    }

    public WebElement getCompareCheckbox() {
        return driver.findElement(compare_checkbox);
    }

    public List<WebElement> getListOfCompareCheckbox() {

        List<WebElement> listOfAddToCompare = driver.findElements(By.cssSelector("div.checkbox.checkbox-btn.select-trim-mobile.listing-comparison-checkbox.compare-checkbox-btn"));
        return listOfAddToCompare;
    }

    public String getPartialAddressFromPage() {

        String fullAddress = driver.findElement(address_location).getText();
        String lastWord = fullAddress.substring(fullAddress.lastIndexOf(" ")+1);
        return lastWord;
    }

    public void findSearchInputAndInsertTextOnly(String text) {
        driver.findElement(textbox_search).sendKeys(text);
    }

    public void searchText(String text) {
        driver.findElement(textbox_search).sendKeys(text);
        driver.findElement(textbox_search).sendKeys(Keys.RETURN);
    }

    public String getSearchingYearValue() {
        String filterValue = driver.findElement(By.cssSelector("div.tag-value")).getText();
        return filterValue;
    }



    public void clickSearchButton() {
        driver.findElement(button_search).sendKeys(Keys.RETURN);
    }

    public void clickCollapseBottomFilter() {driver.findElement(collapse_bottom_filter).click();}

//    public void assertLinkNotPresent(ExtentTest test) {
//        try {
//            driver.findElement(button_clear_all);
//            System.out.println("Link is present");
//            test.fail("Link is present");
//        } catch (NoSuchElementException ex) {
//            /* do nothing, link is not present, assert is passed */
//        }
//    }

    public void clickClearAllButton() {
        driver.findElement(button_clear_all).click();
    }


    // TODO: 2020-01-07 Maybe we need to make this method inside another/global Class? 
    public Boolean isVisibleInViewport(WebElement element) {
        driver = ((RemoteWebElement)element).getWrappedDriver();

        return (Boolean)((JavascriptExecutor)driver).executeScript(
                "var elem = arguments[0],                 " +
                        "  box = elem.getBoundingClientRect(),    " +
                        "  cx = box.left + box.width / 2,         " +
                        "  cy = box.top + box.height / 2,         " +
                        "  e = document.elementFromPoint(cx, cy); " +
                        "for (; e; e = e.parentElement) {         " +
                        "  if (e === elem)                        " +
                        "    return true;                         " +
                        "}                                        " +
                        "return false;                            "
                , element);
    }

    public int getNumbersFromString(String textWithNum) {
        textWithNum = textWithNum.replaceAll("[^0-9]+", "");
        int pureInt = Integer.parseInt(textWithNum);
        return pureInt;
    }

    public String getCarsAvailableTotal() {

        WebElement carsTotal = driver.findElement(By.id("total-vehicle-number"));
        return carsTotal.getText();
    }

    public int getLowestPrice() {
        driver.findElement(sort_by_filter).click();
        driver.findElement(sort_price_asc).click();
        String wholePrice = driver.findElement(vehicle_price).getText();
        return getNumbersFromString(wholePrice);
    }

    public int getHighestPrice() {
        driver.findElement(sort_by_filter).click();
        driver.findElement(sort_price_desc).click();
        String wholePrice = driver.findElement(vehicle_price).getText();
        return getNumbersFromString(wholePrice);
    }



    public void switchToGridView() {
        driver.findElement(switch_grid_view).click();
    }

    public void switchToTableView() {
        driver.findElement(switch_table_view).click();
    }
















}
