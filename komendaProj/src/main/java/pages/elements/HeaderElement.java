package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.MyProfilePage;

public class HeaderElement extends CommonActionsWithElements {
    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = ".//button[@class='btn btn-primary btn-sm']")
    private WebElement buttonSignIn;


    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement buttonSignOut;


    @FindBy(xpath = ".//*[@href=\"/create-post\"]")
    private WebElement buttonCreatePost;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }
    @Step
    public boolean isButtonSignOutDisplayed() {
        webDriverWait15.until(ExpectedConditions.visibilityOf(buttonSignOut));
        return isElementDisplayed(buttonSignOut);
    }

    public boolean isButtonSignInDisplayed() {
        return isElementDisplayed(buttonSignIn);
    }
    @Step
    public MyProfilePage clickOnMyProfileButton(){
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }
    @Step
    public CreatePostPage clickOnCreatePostButton(){
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }
    public WebElement getButtonCreatePost(){
        return buttonCreatePost;
    }

    public boolean isButtonMyProfileDisplayed(){
        return isElementDisplayed(buttonMyProfile);
    }

}
