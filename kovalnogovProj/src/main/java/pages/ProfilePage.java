package pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ProfilePage extends ParentPage {

    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;
    @FindBy(xpath = ".//div[text()='Post successfully deleted']")
    private WebElement deletePostSuccesfull;
    @FindBy(xpath = ".//h2")
    private WebElement user;
    private String titlePost = ".//*[text()='%s']";
    //private String deletePostSuccesfull = ".//*[text()='Post successfully deleted']";
    @FindBy (xpath = ".//a[@class='list-group-item list-group-item-action']")
    private List<WebElement> postsList;

    public ProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeUrl() {
        return "/profile/";
    }

    public List<WebElement> getPostsListWithTitle(String title) {

        return webDriver.findElements(By.xpath(String.format(titlePost, title)));
    }
    @Step
    public ProfilePage checkIsRedirectProfilePage() {
       // checkURL();
        checkURLContainsRelative();
        waitChatToBeHidden();
        Assert.assertTrue("My ProfilePage is not loaded", isElementDisplayed(avatar));
        return new ProfilePage(webDriver);
    }
    @Step
    public ProfilePage checkPostWasCreatred(String postTitle) {
        Assert.assertEquals("Incorrect count of posts", 1, getPostsListWithTitle(postTitle).size());
        return this;
    }
    @Step
    public ProfilePage checkIsUserNameVisibleOnProfilePage(String userName) {
        Assert.assertEquals("Wrong user name on profile Page", userName, getText(user));
        return this;
    }
    @Step
    public ProfilePage deletePostsWithTitleIfPresent(String postTitle) {
        List<WebElement> listOfPosts = getPostsListWithTitle(postTitle);
        int count = listOfPosts.size();
        while (!listOfPosts.isEmpty() && count > 0) {
            clickOnElement(String.format(titlePost, postTitle));
            new PostPage(webDriver).checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
                    .checkIsRedirectProfilePage()
                    .checkPostWasDeletedAlert();
            logger.info("Post was deleted with title" + postTitle);
            listOfPosts = getPostsListWithTitle(postTitle);
            count--;
        }
        if (listOfPosts.size() == 0) {
            logger.info("All posts were deleted with title" + postTitle);
        } else {
            logger.error("Delete is failed");
            Assert.fail("Delete is failed");
        }
        return this;
    }
    @Step
    public ProfilePage checkPostWasDeletedAlert() {
        Assert.assertTrue("Alert that post is deleted not present ", isElementDisplayed(deletePostSuccesfull));
        return this;
    }
    @Step
    public PostPage openPost(String postTitle) {
        clickOnElement(String.format(titlePost, postTitle));
        return new PostPage(webDriver);
    }

    public void checkNumberOfPosts(int expectedNumberOfPosts) {
        Assert.assertEquals("Number of posts", expectedNumberOfPosts, postsList.size());
    }
}
