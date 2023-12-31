package pages;

import libs.TestData;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class LoginPage extends ParentPage{
    @FindBy(xpath = "//input[@name='username' and @placeholder='Username']")
    private WebElement inputUserName;
    @FindBy(xpath = ".//input[@placeholder='Password']")
    private WebElement inputPassword;
    @FindBy (xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonLogin;
    @FindBy (xpath = ".//div[@class='alert alert-danger text-center']")
    private WebElement errorLoginMessage;

    @FindBy (xpath = ".//input[@id = 'username-register']")
    private WebElement registerUserName;

    @FindBy (xpath = ".//input[@id = 'email-register']")
    private WebElement registerEmail;

    @FindBy(xpath = ".//input[@id = 'password-register']")
    private WebElement registerPassword;

    @FindBy (xpath = ".//button[@type= 'submit']")
    private WebElement signUpForOurApp;

    @FindBy(xpath = ALERT_XPATH)
    private List<WebElement> alertMessages;
    public static final String ALERT_XPATH = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    private final String parametrizedAlert = ".//div[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible' and text()='%s']";

    private static final String listOfErrorsLocator = ".//*[@class='alert alert-danger small liveValidateMessage liveValidateMessage--visible']";

    @FindBy(xpath = listOfErrorsLocator)
    private List<WebElement> listOfErrors;

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/";
    }

    public void openLoginPage(){
        try{
            webDriver.get(base_url + getRelativeURL());
            logger.info("Page was opened");
            logger.info(base_url);
        }catch(Exception e){
            logger.error("Can't open Login Page" + e);
            Assert.fail("Can't open Login Page" + e);
        }
    }

    public void enterUserNameIntoInputLogin(String userName) { enterTextInToElement(inputUserName, userName);
    }
    public void enterPasswordIntoInputPassword(String password) {
        enterTextInToElement(inputPassword, password);
    }

    public void clickOnButtonLogin() { clickOnElement(buttonLogin);
    }

    public boolean isSignInButtonDisplayed(){
        return isElementDisplayed(buttonLogin);
    }
    public LoginPage checkTextInLoginErrorMessage(String expectedMessage){
        Assert.assertEquals("Text in success message element"
                , expectedMessage, errorLoginMessage.getText());
        return this;
    }

    public HomePage fillingLoginFormWithValidCred() {
        enterUserNameIntoInputLogin(TestData.VALID_LOGIN);
        enterPasswordIntoInputPassword(TestData.VALID_PASSWORD);
        clickOnButtonLogin();
        return new HomePage(webDriver);
    }

    public LoginPage fillingLoginFormWithInvalidCred(String userName, String password) {
        openLoginPage();
        enterUserNameIntoInputLogin(userName);
        enterPasswordIntoInputPassword(password);
        clickOnButtonLogin();
        return this;
    }


    public void enterPasswordIntoSignUpForm(String password) {enterTextInToElement(registerPassword, password);}

    public void enterEmailIntoSignUpForm(String email) {enterTextInToElement(registerEmail, email);}

    public void enterUserNameIntoSignUpForm(String username) {enterTextInToElement(registerUserName, username);}
    public LoginPage fillingRegistrationFormFields(){
        openLoginPage();
        enterUserNameIntoSignUpForm(TestData.REGISTRATION_INVALID_USERNAME);
        enterEmailIntoSignUpForm(TestData.REGISTRATION_INVALID_EMAIL);
        enterPasswordIntoSignUpForm(TestData.REGISTRATION_INVALID_PASSWORD);
        return this;
    }

    public LoginPage checkValidationMassagesDisplayed() {
        WebDriverWait wdw = new WebDriverWait(webDriver, Duration.ofSeconds(5));
        wdw.until(ExpectedConditions.numberOfElementsToBe(By.xpath(ALERT_XPATH), 3));
        Assert.assertEquals("Validation doesn't work", 3, alertMessages.size());
        return this;
    }

    public LoginPage checkErrorMessageText(String textMessage) {
        Assert.assertTrue("Text \"" + textMessage + "\" not found", isElementDisplayed(String.format(parametrizedAlert, textMessage)));
        return this;
    }
    public void inputFromKeyboard(String text) {
        Actions actions = new Actions(webDriver);
        actions.sendKeys(text).build().perform();
        logger.info(text + " was inputted from keyboard");
    }

    public LoginPage loginFromKeyboard(String login, String password) {
        usersPressesKeyTabTime(2);
        inputFromKeyboard(login);
        usersPressesKeyTabTime(1);
        inputFromKeyboard(password);
        usersPressesKeyEnterTime(1);
        return this;
    }

    public LoginPage registrationFromKeyBoard(String username, String email, String password) {
        openLoginPage();
        usersPressesKeyTabTime(5);
        inputFromKeyboard(username);
        usersPressesKeyTabTime(1);
        inputFromKeyboard(email);
        usersPressesKeyTabTime(1);
        inputFromKeyboard(password);
        return this;
    }


    public LoginPage switchToPreviousTabAndRefresh() {
        ArrayList<String> tabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(tabs.get(0));
        webDriver.navigate().refresh();
        return this;
    }


//    public void checkErrorMessageWithText(String expected) {
//        boolean found = false;
//
//        for (int i = 0; i < alertMessages.size(); i++) {
//            String text = alertMessages.get(i).getText();
//            if (text.equals(expected)) {
//                found = true;
//                break;
//            }
//        }
//
//        Assert.assertTrue("Text \"" + expected + "\" not found", found);
//    }
public LoginPage checkErrorsMessages(String expectedErrors) {
    String[] expectedErrorsArray = expectedErrors.split(",");
    webDriverWait10
            .withMessage("Number of messages should be " + expectedErrorsArray.length)
            .until(ExpectedConditions
                    .numberOfElementsToBe(By.xpath(listOfErrorsLocator), expectedErrorsArray.length));

    ArrayList <String> actualTextFromErrors = new ArrayList<>();
    for (WebElement element: listOfErrors){
        actualTextFromErrors.add(element.getText());
    }
    SoftAssertions softAssertions = new SoftAssertions();
    for (int i = 0; i < expectedErrorsArray.length; i++){
        softAssertions.assertThat(expectedErrorsArray[i]).as("Message is not equal").isIn(actualTextFromErrors);
    }
    softAssertions.assertAll();
    return this;
}

}
