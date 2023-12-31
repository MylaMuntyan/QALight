package pages;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreatePostPage extends ParentPage {

    @FindBy(name = "title")
    private WebElement inputTitle;

    @FindBy(name = "body")
    private WebElement inputBody;

    @FindBy(xpath = ".//form[@action='/create-post']//button")
    private WebElement buttonSavePost;

    @FindBy(tagName = "select")
    private WebElement dropDownOptions;

    @FindBy(xpath = ".//input[@type='checkbox' and @name='uniquePost']")
    private WebElement checkboxUnique;


    public CreatePostPage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/create-post";
    }

    public CreatePostPage checkIsRedirectToCreatePostPage() {
        checkURL();
        waitChatToHide();
        Assert.assertTrue("CreatePostPage is not loaded",
                isElementDisplayed(inputTitle));
        return this;
    }

    public CreatePostPage enterTextInInputTitle(String postTitle) {
        enterTextIntoElement(inputTitle, postTitle);
        return this;
    }

    public CreatePostPage enterTextInInputBody(String bodyText) {
        enterTextIntoElement(inputBody, bodyText);
        return this;
    }

    public PostPage clickOnSavePostButton() {
        clickOnElement(buttonSavePost);
        return new PostPage(webDriver);
    }

    public CreatePostPage selectTextInDropDownOptions(String textInDropDown) {
        selectTextInDropDown(dropDownOptions, textInDropDown);
        return this;
    }

    public CreatePostPage selectValueInDropDownOptions(String valueINDD){
        selectValueInDropDown(dropDownOptions, valueINDD);
        return this;
    }

    public CreatePostPage selectValueInDropDownByUI(String ddValue) {
        selectTextInDropDownByUI(dropDownOptions, ddValue);
        return this;
    }

    public CreatePostPage selectUniqueCheckbox(String state) {
        setCheckBoxToState(checkboxUnique, state);
        return this;
    }
}
