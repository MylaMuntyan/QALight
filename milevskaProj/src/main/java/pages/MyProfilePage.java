package pages;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class MyProfilePage extends ParentPage{
    @FindBy(xpath = ".//img[@class='avatar-small']")
    private WebElement avatar;
    private String titlePost = ".//*[text()='%s']";
    @FindBy(xpath = ".//*[text()='Post successfully deleted']")
    private WebElement successDeletePostMessage;


    @FindBy(xpath = ".//div[@class='container py-md-5 container--narrow']//h2")
    private WebElement loggedUser;

    public MyProfilePage(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    String getRelativeURL() {
        return "/profile/";
    }

    public MyProfilePage checkIsRedirectToMyProfilePage() {
        checkURL();
        waitChatToBeHide();
        Assert.assertTrue("My profile page is not loaded", isElementDisplayed(avatar));
        return this;
    }
 public List<WebElement> getPostsListWithTitle(String title){
        return webDriver.findElements(By.xpath(String.format(titlePost, title)));
 }
    public MyProfilePage checkPostWasCreated(String postTitle) {
        Assert.assertEquals("Number of posts with title", 1, getPostsListWithTitle(postTitle).size());
        return this;
    }

    public MyProfilePage checkIsCorrectUserLogin(String username){
        Assert.assertEquals("This not correct user", username, loggedUser.getText());
        return this;
    }

    public MyProfilePage deletePostsWithTitleTillPresent(String postTitle){
        List <WebElement> listOfPosts = getPostsListWithTitle(postTitle);
        int counter = listOfPosts.size();
        while(!listOfPosts.isEmpty() && counter>0){
            clickOnElement(String.format(titlePost, postTitle));
            new PostPage(webDriver).checkIsRedirectToPostPage()
                    .clickOnDeleteButton()
            .checkIsRedirectToMyProfilePage()
                    .checkIsSuccessDeletePostMessagePresent();

            logger.info("Post was deleted with title" + postTitle);
            listOfPosts = getPostsListWithTitle(postTitle);
            counter--;
        }
        if (listOfPosts.size() ==0) {
            logger.info("All posts was deleted with title" + postTitle);
        }else{
            logger.error("Delete fail");
            Assert.fail("Delete fail");
        }
        return this;
    }

    private MyProfilePage checkIsSuccessDeletePostMessagePresent() {
        Assert.assertTrue("Messsage delete Post is not displayed ", isElementDisplayed(successDeletePostMessage));
        return this;
    }
}
