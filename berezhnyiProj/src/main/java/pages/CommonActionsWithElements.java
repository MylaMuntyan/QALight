package pages;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class CommonActionsWithElements {
    protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    WebDriverWait webDriverWait10, webDriverWait15;
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);


    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);

        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_HIGH()));
    }

    protected void enterTextIntoElement(WebElement webElement, String text){
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element" + getElementName(webElement));
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement){
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            String name = getElementName(webElement);
            webElement.click();
            logger.info(name + " Element was clicked");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(String xpath){
        try {
            clickOnElement(webDriver.findElement(By.xpath(xpath)));
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }



    protected void selectTextInDropDown(WebElement dropDown, String visibleText){
        try{
            Select select = new Select(dropDown);
            select.selectByVisibleText(visibleText);
            logger.info(visibleText + "was selected in Drop-down");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void selectValueInDropDown(WebElement dropDown, String value){
        try {
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in Drop Down");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }

    }

    protected void selectTextInDropDownByUI(WebElement dropDown, String textValue){
        try {
            clickOnElement(dropDown);
            clickOnElement(dropDown.findElement(By.xpath(String.format(".//option[text()='%s']", textValue))));
            logger.info(textValue + " was selected in dropDown");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected boolean isElementDisplayed(WebElement webElement){
        try {

            boolean state = webElement.isDisplayed();
            String message;
            if (state){
                message = getElementName(webElement) + " Element is displayed";
            }else {
                message = getElementName(webElement) + " Element is not displayed";
            }
            logger.info(message);
            return state;
        }catch(Exception e){
            logger.info("Element is not displayed");
            return false;
        }
    }

    protected boolean isElementDisplayed(String locator){
        try {
            return webDriver.findElement(By.xpath(locator)).isDisplayed();
        }catch (Exception e){
            logger.info("Element is not displayed");
            return false;
        }
    }

    private String getElementName(WebElement webElement){
        try {
            return webElement.getAccessibleName();
        }catch (Exception e){
            return "";
        }
    }

    protected void printErrorAndStopTest(Exception e){
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    private void selectCheckBox(WebElement checkBox) {
        if (!checkBox.isSelected()){
            clickOnElement(checkBox);
        } else {
            logger.info("Checkbox is selected");
        }
    }

    private void deselectCheckBox(WebElement checkBox) {
        if (checkBox.isSelected()){
            clickOnElement(checkBox);
        } else {
            logger.info("Checkbox ix deselected");
        }
    }

    protected void setCheckBoxToState(WebElement checkBox, String state){
        try {
            if (state.equalsIgnoreCase("check")){
                selectCheckBox(checkBox);
            } else if (state.equalsIgnoreCase("uncheck")) {
                deselectCheckBox(checkBox);
            }

        }catch (Exception e){
            logger.info("Checkbox could not be selected");
            printErrorAndStopTest(e);
        }
    }



    public void usersPressesKeyEnterTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.ENTER).build().perform();
        }
    }

    public void usersPressesKeyTabTime(int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(Keys.TAB).build().perform();
        }

    }

    public void usersPressesKeyTime(Keys keys, int numberOfTimes) {
        Actions actions = new Actions(webDriver);
        for (int i = 0; i < numberOfTimes; i++) {
            actions.sendKeys(keys).build().perform();
        }

    }

    public void userOpensNewTab() {
        ((JavascriptExecutor)webDriver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<> (webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(1));
    }


//
//    метод moveToElement (аналог скрола )
//
//    WebElement element = driver.findElement(By.id("my-id"));
//    Actions actions = new Actions(driver);
//actions.moveToElement(element);
//actions.perform();
//
//—————————-
//    метод скрола з використанням javaScript
//
//    JavascriptExecutor js = (JavascriptExecutor) driver;
//        js.executeScript("javascript:window.scrollBy(250,350)");
//
//—————————-
//    Емуляція натискання PageDown
//
//WebElement.sendKeys(Keys.DOWN);
//
//—————————-
//    скрол до елемента з javaScript
//
//            webElement = driver.findElement(By.xpath("bla-bla-bla"));
//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);


}
