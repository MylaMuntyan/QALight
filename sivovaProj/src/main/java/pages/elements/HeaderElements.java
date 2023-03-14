package pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.CommonActionsWithElements;
import pages.CreatePostPage;
import pages.HomePage;
import pages.MyProfilePage;

public class HeaderElements extends CommonActionsWithElements {
    @FindBy(xpath = ".//a[@href='/profile/newqaauto' and @class='mr-2']")
    private WebElement buttonMyProfile;

    @FindBy (xpath = ".//a[@href='/create-post']")
    private WebElement buttonCreatePost;

    @FindBy(xpath = ".//button[text()='Sign Out']") private WebElement buttonSignOut;

    public HeaderElements(WebDriver webDriver) {
        super(webDriver);
    }

    @Step
    public MyProfilePage clickMyProfileButton() {
        clickElement(buttonMyProfile);
        return new MyProfilePage(webDriver);
    }

    @Step
    public CreatePostPage clickOnCreatePostButton() {
        clickElement(buttonCreatePost);
        return new CreatePostPage(webDriver);
    }

    @Step
    public boolean isButtonSignOutDisplayed () {
        try {
            return buttonSignOut.isDisplayed();
        } catch (Exception e) {
            return false;
        }

    }

}
