package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage {
    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;

    @FindBy(xpath = ".//div[text()='Post successfully deleted']")
    private WebElement successDeletePostMessage;

    @FindBy(xpath = ".//h2")
    private WebElement userNameLocator;

    private String titlePost = ".//*[text()='%s']";

    @FindBy (xpath = ".//a[@class='list-group-item list-group-item-action']")
    private List<WebElement> postsList;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/profile/";
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkURLContainsRelative();
        waitChatToBeHide();
        Assert.assertTrue("MyProfilePage is not loaded ", isElementDisplayed(avatar));
        return this;
    }

    public List<WebElement> getPostsListWithTitle(String title) {
        return webDriver.findElements(By.xpath(String.format(titlePost, title)));
    }

    public MyProfilePage checkPostWasCreated(String postTitle) {
        Assert.assertEquals("Number of posts with title", 1, getPostsListWithTitle(postTitle).size());
        return this;
    }

    public MyProfilePage deletePostsWithTitleTillPresent(String postTitle) {
        List<WebElement> listOfPosts = getPostsListWithTitle(postTitle);
        int counter = listOfPosts.size();
        while(!listOfPosts.isEmpty() && counter > 0){
            clickOnElement(String.format(titlePost, postTitle));
            new PostPage(webDriver)
                    .checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessDeletePostMessagePresent()
            ;
            logger.info("Post was deleted with title " + postTitle);
            listOfPosts = getPostsListWithTitle(postTitle);
            counter--;//counter=counter- 1
        }
        if (listOfPosts.size() == 0) {
            logger.info("All posts were deleted with title " + postTitle);
        }else {
            logger.error("Delete fail");
            Assert.fail("Delete fail");
        }
        return this;
    }

    private MyProfilePage checkIsSuccessDeletePostMessagePresent() {
    Assert.assertTrue("Message delete Post is not displayed", isElementDisplayed(successDeletePostMessage));
        return this;
    }

    public MyProfilePage checkUserNameOnProfilePage(String userName) {
        Assert.assertTrue("User name is not valid", userNameLocator.getText().contains(userName));
        return this;
    }

    public PostPage clickOnPostTitle(String postTitle){
        clickOnElement(String.format(titlePost,postTitle));
        return new PostPage(webDriver);
    }

    public MyProfilePage checkPostWithNewTitleWasCreated(String newPostTitle) {
        Assert.assertEquals("Post is not one", 1, getPostsListWithTitle(newPostTitle).size());
        logger.info("Post was created");
        return this;
    }

    public void checkNumberOfPosts(int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts ", expectedNumberOfPosts, postsList.size());
    }
}
