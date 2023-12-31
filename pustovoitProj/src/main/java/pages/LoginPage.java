package pages;

import io.qameta.allure.Step;
import libs.TestData;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;

    @FindBy(id = "username-register")
    private WebElement inputLoginRegistration;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;

    private static final String listOfErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = listOfErrorsLocator)
    private List<WebElement> listOfErrors;

    @FindBy(xpath = ".//*[@class = 'alert alert-danger text-center']")
    private WebElement errorMessageForInvalidLogIn;

    @FindBy(xpath = ".//*[contains(@class,'alert alert-danger text-center')]")
    private WebElement alertInCenter;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    @Step
    public void openLoginPage() {
        try {
            webDriver.get(base_url + getRelativeURL());
            logger.info("LoginPage was opened");
            logger.info(base_url);
        } catch (Exception e) {
            logger.error("Can not open Login Page" + e);
            Assert.fail("Can not open Login Page" + e);
        }
    }

    @Step
    public void enterUserNameIntoInputLogin(String userName) {
//        try {
////            WebElement inputUserName =
////                    webDriver.findElement(By.xpath(".//input[@name='username' and @placeholder='Username']"));
//            inputUserName.clear();
//            inputUserName.sendKeys(userName);
//            logger.info("login was inputted");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputUserName, userName);
    }

    @Step
    public LoginPage enterPaswordIntoInputPassword(String password) {
//        try {
////            WebElement inputPassword =
////                    webDriver.findElement(By.xpath(".//input[@placeholder='Password']"));
//            inputPassword.clear();
//            inputPassword.sendKeys(password);
//            logger.info("Password was entered");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        enterTextIntoElement(inputPassword, password);
        return this;
    }

    @Step
    public void clickOnButtonLogin() {
//        try {
////            WebElement buttonLogin =
////                    webDriver.findElement(By.xpath(".//button[@class='btn btn-primary btn-sm']"));
//            buttonLogin.click();
//            logger.info("Button was clicked");
//        } catch (Exception e) {
//            printErrorAndStopTest(e);
//        }
        clickOnElement(buttonLogin);
    }

    @Step
    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPaswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }
    @Step
    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonLogin);
    }
    @Step
    public LoginPage enterUserNameInRegistrationForm(String userName) {
        enterTextIntoElement(inputLoginRegistration, userName);
        return this;
    }
    @Step
    public LoginPage enterEmailInRegistrationForm(String email) {
        enterTextIntoElement(inputEmailRegistration, email);
        return this;
    }
    @Step
    public LoginPage enterPasswordInRegistrationForm(String password) {
        enterTextIntoElement(inputPasswordRegistration, password);
        return this;
    }
    @Step
    public LoginPage checkErrorsMessages(String expectedErrors) {
        String[] expectedErrorsArray = expectedErrors.split(",");
        webDriverWait10
                .withMessage("Number of messages should be " + expectedErrorsArray.length)
                .until(ExpectedConditions
                        .numberOfElementsToBe(By.xpath(listOfErrorsLocator), expectedErrorsArray.length));
        Util.waitABit(1);
        Assert.assertEquals("Number of message", expectedErrorsArray.length, listOfErrors.size());
        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element : listOfErrors) {
            actualTextFromErrors.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorsArray.length; i++) {
            softAssertions.assertThat(expectedErrorsArray[i]).as("Message is not equals").isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();

        return this;
    }

    @Step
    public LoginPage checkErrorMessageForInvalidLogin(String expectedMessage) {
        Assert.assertTrue("Message is not displayed", isElementDisplayed(errorMessageForInvalidLogIn));
        Assert.assertEquals("Message is not found with this text" + expectedMessage, expectedMessage, errorMessageForInvalidLogIn.getText());
        return this;
    }

    public void checkAlertInCenter(String expectedText) {
        Assert.assertEquals("Message in Alert", expectedText, alertInCenter.getText());
    }
}
