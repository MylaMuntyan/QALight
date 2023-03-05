package pages;

import libs.TestData;
import libs.Util;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage {
    @FindBy(xpath = ".//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;

    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPass;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonSignIn;

    @FindBy(id = "username-register")
    private WebElement inputLoginRegistration;

    @FindBy(id = "email-register")
    private WebElement inputEmailRegistration;

    @FindBy(id = "password-register")
    private WebElement inputPasswordRegistration;

    @FindBy(xpath = listOfErrorsLocator)
    private List<WebElement> listOfErrors;

    @FindBy(xpath = xpathOfAlerts)
    private List<WebElement> alertMessages;

    public static final String xpathOfAlerts = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    private final String parameterizedAlert = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='%s']";

    private static final  String listOfErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    public void openLoginPage() {
        try {
            webDriver.get(base_url + getRelativeURL());
            logger.info("LoginPage was opened");
            logger.info(base_url);
        } catch (Exception e) {
            logger.error("Can't open Login Page " + e);
            Assert.fail("Can't open Login Page " + e);
        }
    }

    public void enterUserNameintoInputLogin(String userName) {
        enterTextToElement(inputUserName, userName);
    }

    public void enterPasswordIntoInputPassword(String password) {
        enterTextToElement(inputPass, password);
    }

    public void clickOnButtonLogin() {
        clickOnElement(buttonSignIn);
    }

    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameintoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }

    public HomePage fillingLoginFormWithInvalidCred() {
        openLoginPage();
        enterUserNameintoInputLogin(TestData.INVALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.INVALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }

    public boolean isButtonSignInDisplayed() {
        return isButtonDisplayed(buttonSignIn);
    }

    public LoginPage enterUserNameintoRegistrationForm(String userName) {
        enterTextToElement(inputLoginRegistration, userName);
        return this;
    }

    public LoginPage enterEmailInRegistrationForm(String email){
        enterTextToElement(inputEmailRegistration, email);
        return this;
    }

    public LoginPage enterPasswordInRegistrationForm(String pass){
        enterTextToElement(inputPasswordRegistration, pass);
        return this;
    }

    public LoginPage checkErrorMessage(String expectedErrors) {
        String[]expectedErrorArray = expectedErrors.split(",");
        webDriverWait10
                .withMessage("Number of messages should be" + expectedErrorArray.length)
                .until(ExpectedConditions.
                        numberOfElementsToBe(By.xpath(listOfErrorsLocator),expectedErrorArray.length));
        Util.waitABit(1);
        Assert.assertEquals("Number of messages", expectedErrorArray.length, listOfErrors.size());

        ArrayList<String> actualTextFromErrors = new ArrayList<>();
        for (WebElement element: listOfErrors){
            actualTextFromErrors.add(element.getText());
        }

        SoftAssertions softAssertions = new SoftAssertions();
        for (int i = 0; i < expectedErrorArray.length; i++) {
            softAssertions.assertThat(expectedErrorArray[i]).as("Message is not equals").isIn(actualTextFromErrors);
        }
        softAssertions.assertAll();



        return this;
    }

    public LoginPage checkCounterOfAllerts() {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(7));
        webDriverWait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(xpathOfAlerts), 3));
        Assert.assertEquals("Some alert is not shown ", 3, alertMessages.size());
        return this;
    }

    public LoginPage checkErrorMessageWithText(String textMessage) {
        Assert.assertTrue("Text \"" + textMessage + "\" not found", isElementDisplayed(String.format(parameterizedAlert, textMessage)));
        return this;
    }
}
