package utilities;
import browserfactory.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Utility extends BaseTest {

    //  Declaration of Action for moser hover
    public static Actions action;

    // This method will get web element
    public WebElement getWebElement(By by) {
        return driver.findElement(by);
    }

    // This method will get list of we elements
    public List<WebElement> getWebElements(By by) {
        return driver.findElements(by);
    }

    // This method will click on element
    public void clickOnElement(By by) {
        WebElement loginLink = driver.findElement(by);
        loginLink.click();
    }

    // this method will get text from element
    public String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    // this method will send text on element
    public void sendTextToElement(By by, String inputText) {
        driver.findElement(by).sendKeys(inputText);
    }

    // this method will send text on element
    public void sendTextToElementWithClearText(By by, String inputText) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(inputText);
    }


    //********************Mouser hover action*************************

    public void mouseHoverToElement(By by){
        action = new Actions(driver);
        action.moveToElement(getWebElement(by)).build().perform();
    }

    public void mouseHoverToElementAndClick(By by){
        action = new Actions(driver);
        action.moveToElement(getWebElement(by)).click().build().perform();
    }


    //************************ method for dropdown with Select class******************

    // set dropdown by value
    public void selectByValueDropdown(By by, String value){
        Select select = new Select(getWebElement(by));
        select.selectByValue(value);
    }

    // set dropdown by index
    public void selectByIndexDropdown(By by, int index){
        Select select = new Select(getWebElement(by));
        select.selectByIndex(index);
    }

    // set dropdown by visible text
    public void selectByVisibleTextDropdown(By by, String visibleText){
        Select select = new Select(getWebElement(by));
        select.selectByVisibleText(visibleText);
    }

    // verify value is alphabetically in order or not
    public boolean isSortedAlphabetically(String[] items) {
        for (int i = 0; i < items.length - 1; i++) {
            if (items[i].compareToIgnoreCase(items[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    //Check if the prices are sorted in ascending order
    public boolean isSortedAscending(List<Double> values) {
        for (int i = 0; i < values.size() - 1; i++) {
            if (values.get(i) > values.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

}
