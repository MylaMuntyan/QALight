package StepDefinitions;


import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import libraries.DriverHelper;
import pages.HomePage;
import pages.MyProfilePage;

public class MyProfilePage_StepDefinitions {
HomePage homePage = new HomePage(DriverHelper.getWebDriver());
MyProfilePage myProfilePage = new MyProfilePage(DriverHelper.getWebDriver());

    @Then("^User was redirected to 'MyProfile' page$")
    public void userWasRedirectedToProfilePage() {
        myProfilePage.checkIsRedirectToMyProfilePage();
    }

    @And("^User sees (\\d+) posts in Posts list on 'MyProfile' page$")
    public void userSeesPostsInPostsListOnMyProfilePage(int expectedNumberOfPosts) {
        myProfilePage.checkNumberOfPosts(expectedNumberOfPosts);
    }
}
