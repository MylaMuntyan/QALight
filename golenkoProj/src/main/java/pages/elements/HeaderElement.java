package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.MyProfilePage;

public class HeaderElement extends CommonActionsWithElements {

    @FindBy(xpath = ".//*[@data-original-title='My Profile']")
    private WebElement buttonMyProfile;

    @FindBy(xpath = ".//button[text()='Sign Out']")
    private WebElement signOutButton;

    @FindBy (xpath = ".//*[@href='/create-post']")
    private WebElement buttonCreatePost;

    @FindBy (xpath = ".//*[@data-original-title='My Profile']")
    private WebElement avatar;

    public HeaderElement(WebDriver webDriver) {
        super(webDriver);
    }

    @Step
    public MyProfilePage clickOnMyProfileButton(){
        clickOnElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    @Step
    public boolean isButtonSignOutDisplayed() {
        return isElementDisplayed(signOutButton);
    }

    @Step
    public CreatePostPage clickOnCreatePostButton() {
        clickOnElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    public boolean isProfileAvatarDisplayed() {
        return isElementDisplayed(avatar);
    }
}
