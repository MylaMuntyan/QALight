package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.elements.HeaderElement;

public class PostPage extends ParentPage {

    @FindBy (xpath = ".//a[@data-original-title=\"Edit\"]")
    private WebElement buttonEdit;

    @FindBy (xpath = ".//div[@class='alert alert-success text-center']")
    private WebElement successMessage;

    @FindBy (xpath = ".//div[@class='d-flex justify-content-between']//h2")
    private WebElement postTitle;

    @FindBy (xpath = "(.//div[@class='body-content']//p)[2]")
    private WebElement postNote;

    @FindBy (xpath = ".//u[contains(text(), 'One Person')]")    // was "All Users"
    private WebElement postNoteSecondOption;

    @FindBy (xpath = ".//button[@class='delete-post-button text-danger']")
    private WebElement buttonDelete;

    @FindBy (xpath = ".//p[contains(text(), 'Is this post unique? : yes')]")
    private WebElement indicatorOfUniquePost;



    private HeaderElement headerElement = new HeaderElement(webDriver); //  оголошує елемент headerElement


    public PostPage(WebDriver webDriver) {
        super(webDriver);
    }     //locator



    @Override
    String getRelativeURL() {    // адреса сторінка поста
        return "/post/";
    }


    public HeaderElement getHeaderElement() {   //  метод звернення до елемента headerElement
        return headerElement;
    }



    public PostPage checkIsRedirectToPostPage() {
        checkURLContainsRelative();
        waitChatToBeHide();
        Assert.assertTrue("PostPage is not loaded", isElementDisplayed(buttonEdit));
        return this;
    }
    public PostPage checkInSuccessMessage(String expectedMessage){
        Assert.assertEquals("Text in success message element", expectedMessage, successMessage.getText());

        return this;
    }
    public PostPage checkTitleIsVisible() {
        Assert.assertTrue("Title is not visible", isElementDisplayed(postTitle));
        return this;
    }
    public PostPage checkNoteIsVisibleByText(String expectedText){                                   // переписати на перевірку по тексту поста
        Assert.assertEquals("Note text is not visible", expectedText, postNote.getText());

        return this;
    }
    public PostPage checkNoteIsVisible() {
        Assert.assertTrue("Note is not visible", isElementDisplayed(postNote));
        return this;
    }
    public PostPage  checkNoteSecondOptionIsVisible() {
        Assert.assertTrue("SecondOption is not visible", isElementDisplayed(postNoteSecondOption));
        return this;
    }

    public PostPage checkWasCheckBoxChecked() {
        Assert.assertTrue("Checkbox was not checked", isElementDisplayed(indicatorOfUniquePost));
        return this;
    }



    public MyProfilePage clickOnDeleteButton() {
        clickOnElement(buttonDelete);

        return new MyProfilePage(webDriver);
    }


    public PostEditPage clickOnEditButton() {
        clickOnElement(buttonEdit);

        return new PostEditPage(webDriver);
    }
}
