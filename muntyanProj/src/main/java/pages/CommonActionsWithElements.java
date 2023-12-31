package pages;

import libs.ConfigProperties;
import org.aeonbits.owner.ConfigFactory;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CommonActionsWithElements {
   protected WebDriver webDriver;
    Logger logger = Logger.getLogger(getClass());
    WebDriverWait webDriverWait10, webDriverWait15;
    public static ConfigProperties configProperties = ConfigFactory.create(ConfigProperties.class);

    public CommonActionsWithElements(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
        webDriverWait10 = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriverWait15 = new WebDriverWait(webDriver, Duration.ofSeconds(15));

    }

    protected void enterTextInToElement(WebElement webElement, String text) {
        try {
            webDriverWait15.until(ExpectedConditions.visibilityOf(webElement));
            webElement.clear();
            webElement.sendKeys(text);
            logger.info(text + " was inputted into element " + getElementName(webElement));
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement(WebElement webElement) {
        try {
            webDriverWait15.until(ExpectedConditions.elementToBeClickable(webElement));
            String name = getElementName(webElement);
            webElement.click();
            logger.info(name + " Element was clicked");
        } catch (Exception e) {
            printErrorAndStopTest(e);
        }
    }

    protected void clickOnElement (String xpath)
    {
        try {
            clickOnElement(webDriver.findElement(By.xpath(xpath)));
        }catch (Exception e){
            printErrorAndStopTest(e);
        }
    }


    protected boolean isElementDisplayed(String locator) {
        try {
            return isElementDisplayed(webDriver.findElement(By.xpath(locator)));
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }
    protected boolean isElementDisplayed(WebElement webElement) {
        try {
            boolean state = webElement.isDisplayed();
            String message;
            if (state) {
                message = getElementName(webElement) + " Element is displayed";
            } else {
                message = getElementName(webElement) + " Element is not displayed";
            }
            logger.info(getElementName(webElement) + " Element is displayed");
            return state;
        } catch (Exception e) {
            logger.info("Element is not displayed");
            return false;
        }
    }

//    protected void forTextComparing(String expectedText, WebElement webElement) {
//        try {
//            Assert.assertEquals("Text does not mach", expectedText, webElement.getText());
//            logger.info(expectedText + " found its mach.");
//        } catch (Exception e) {
//            logger.info(expectedText + " does not found its mach.");
//        }
//    }


    protected void selectTextInDropDown(WebElement dropDown, String visibleText){
        try{
            Select select = new Select(dropDown); //in SElect methods to work with drop-downs
            select.selectByVisibleText(visibleText);
            logger.info(visibleText + " was selected in drop-down");
        }catch (Exception e){
            printErrorAndStopTest(e);
        }

    }

    protected void selectValueInDropDown (WebElement dropDown, String value){
        try{
            Select select = new Select(dropDown);
            select.selectByValue(value);
            logger.info(value + " was selected in drop down");
        }catch (Exception e ){
            printErrorAndStopTest(e);
        }

    }



    private String getElementName(WebElement webElement) {
        try{
            return webElement.getAccessibleName();
        }catch (Exception e) {
            return "";
        }
    }
    protected void printErrorAndStopTest(Exception e) {
        logger.error("Can not work with element " + e);
        Assert.fail("Can not work with element " + e);
    }

    protected void checkCheckbox(WebElement checkbox) {
        if (!isCheckboxSelected(checkbox)) {
            clickOnElement(checkbox);
            logger.info("Checkbox is selected");
        } else {
            logger.info("Checkbox was already selected");
        }
    }

    protected void uncheckCheckbox(WebElement checkbox){
        if (isCheckboxSelected(checkbox)) {
            checkCheckbox(checkbox);
            logger.info("Checkbox is unselected");
        } else {
            logger.info("Checkbox was already unselected");
        }
    }
    protected void changeCheckboxStatus (WebElement checkbox, String targetValue){
        if (targetValue.equalsIgnoreCase("check")) {
            checkCheckbox(checkbox);
        } else {
            if (targetValue.equalsIgnoreCase("uncheck")) {
                uncheckCheckbox(checkbox);
            } else {
                logger.info("Incorrect target value: input check or uncheck ");
                Assert.fail("Incorrect target value: input check or uncheck ");
            }
        }
    }

    protected boolean isCheckboxSelected (WebElement checkbox) {
        return checkbox.isSelected();
    }





}
