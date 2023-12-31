package postTest;

import baseTest.BaseTest;
import libraries.Util;
import org.junit.After;
import org.junit.Test;

public class CreatePostTest extends BaseTest {
    final String POST_TITLE = "TC1_soloviova_" + Util.getDateAndTimeFormatted();
    final String BODY_TITLE = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam mattis faucibus malesuada. Pellentesque pretium eget felis sit amet pretium. Maecenas sollicitudin aliquet dignissim. Sed aliquam lorem non elementum tincidunt. Curabitur varius risus eu nibh porta pellentesque. Phasellus mollis sed dui sed facilisis.";
    final String check = "check";
    final String uncheck = "uncheck";
    @Test
    public void TC1_createNewPost() {
        homePage
                .openHomePage()
                .getHeaderElements().clickOnCreatePostButton()
                .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody(BODY_TITLE)
                .checkboxState(uncheck)
             //   .selectTextInDropDownOptions("Приватне повідомлення")
              //  .selectValueInDropDownOptions("One Person")
                .selectTextInDropDownWithUI()
                .clickOnSaveNewPostButton()
                .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .checkTextInTitleElement(POST_TITLE)
                .checkIsLabelPresent()
                .checkLabelValue("One Person")
                .checkIfPostUnique(uncheck)
                .getHeaderElements().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .checkUserName("qaauto")
                .checkPostWasCreated(POST_TITLE)
        ;
    }

    @After
    public void deletePost(){
        homePage
                .openHomePage()
                .getHeaderElements().clickOnMyProfileButton()
                .checkIsRedirectToMyProfilePage()
                .deletePostsWithTitleTillPresent(POST_TITLE)
        ;
    }
}
