package pages;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
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
    Logger logger=Logger.getLogger(getClass());
    WebDriverWait webDriverWait10;
    WebDriverWait webDriverWait15;

    public static ConfigProperties configProperties= ConfigFactory.create(ConfigProperties.class);


    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
        webDriverWait10=new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_LOW()));
        webDriverWait15=new WebDriverWait(webDriver, Duration.ofSeconds(configProperties.TIME_FOR_EXPLICIT_WAIT_HIGH()));
    }


    protected void enterTextIntiElement(WebElement webElement, String text){
        try {
            webDriverWait10.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text+"was inputted into element"+getElementName(webElement));
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }









    protected void printErrorAndStopTest(Exception e){
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }



    protected void clickOnElement(WebElement webElement){
        try {
            webDriverWait10.until(ExpectedConditions.elementToBeClickable(webElement));
            String name=getElementName(webElement);
            webElement.click();
            logger.info(name+" Element was clicked");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(String xPath){
        try {
            clickOnElement(webDriver.findElement(By.xpath(xPath)));
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }



    protected void selectTextInDropdown(WebElement dropDown, String vivibleText){
        try {
            Select select=new Select(dropDown);
            select.selectByVisibleText(vivibleText);
            logger.info(vivibleText+" was selected in Dropdown");

        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }



    protected void selectValueInDropdown(WebElement dropDown, String value){
        try {
            Select select=new Select(dropDown);
            select.selectByValue(value);
            logger.info(value+" was selected in Dropdown");

        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }



    protected void selectTextInDropDownByUI (WebElement dropDown, String textInDropdown){
        try {
            dropDown.click();
            String selectedXpath=String.format(".//option[text()=\"%s\"]",textInDropdown);
            webDriver.findElement(By.xpath(selectedXpath)).click();
            logger.info(textInDropdown+" was selected in Dropdown");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }




    protected boolean isElementDisplayed(String text) {
        WebElement webElement=webDriver.findElement(By.xpath(text));
        return isElementDisplayed(webElement);
    }





    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state= webElement.isDisplayed();
            String message;

            if(state){
                message=getElementName(webElement)+" Element is displayed";
            }else {
                message=getElementName(webElement)+" Element is not displayed";
            }

            logger.info(message);
            return state;

        } catch (Exception e) {
            logger.info("element is not displayed");
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



    protected void clickOnCheckBoxEnable(WebElement webElement){
        try {
            if(!webElement.isSelected()){
                clickOnElement(webElement);
            }else {
                logger.info("Checkbox "+webElement.getText()+"is already selected");
            }
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }


    protected void clickOnCheckBoxDisable(WebElement webElement){
        try {
            if(webElement.isSelected()){
            clickOnElement(webElement);
            }else {
                logger.info("Checkbox "+webElement.getText()+"is not already selected");
            }
        }catch (Exception e){
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
//WebElement.sendKeys(Keys.PAGE_DOWN);
//
//—————————-
//    скрол до елемента з javaScript
//
//            webElement = driver.findElement(By.xpath("bla-bla-bla"));
//((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", webElement);









}

