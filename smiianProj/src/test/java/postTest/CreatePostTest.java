package postTest;

import baseTest.BaseTest;
import libs.TestData;
import libs.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_smiian_1" + Util.getDateAndTimeFormatted();
    final String POST_BODY = "text-text-text";

    @Test
    public void TC1_createNewPost(){
        homePage
                .openHomePage()
                .getHeaderElement().clickOnCreatePostButton()
             .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInBodyContent(POST_BODY)
                .setUniquePostCheckboxCheckState()
                .selectSecondTextInDropDownByUi()
                .clickOnSavePostButton()
             .checkInSuccessMessage("New post successfully created.")
             .checkTitleIsVisible()
             .checkNoteIsVisibleByText(POST_BODY)
             .checkNoteIsVisible()
             .checkWasCheckBoxChecked()
             .checkNoteSecondOptionIsVisible()
                .getHeaderElement().clickOnMyProfileButton()
             .checkIsRedirectToMyProfilePage()
             .checkPostWasCreated(POST_TITLE)
             .checkIsLoggedUserNameOnPage(TestData.VALID_LIGIN)
        ;
    }



    @After
    public void deletePost() {
        homePage
                .openHomePage()
                .getHeaderElement().clickOnMyProfileButton()
             .checkIsRedirectToMyProfilePage()
                .deletePostWithTitleTillPresent(POST_TITLE)
        ;

    }
}