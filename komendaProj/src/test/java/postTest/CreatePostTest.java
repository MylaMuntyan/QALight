package postTest;

import baseTest.BaseTest;
import org.junit.Test;

public class CreatePostTest  extends BaseTest {
    final String POST_TITLE = "TC1_komenda";
    @Test
    public void TC1_createNewPost(){
        homePage
                .openHomePage()
                .clickOnCreatePostButton()
            .checkIsRedirectToCreatePostPage()
                .enterTextInInputTitle(POST_TITLE)
                .enterTextInInputBody("Body text")
//               .selectTextInDropDownOptions("Приватне повідомлення")
                .selectValueInDropDownOptions("One Person")
                .clickOnSavePostButton()
            .checkIsRedirectToPostPage()
                .checkTextInSuccessMessage("New post successfully created.")
                .getHeaderElement().clickOnMyProfileButton()
            .checkIsRedirectToMyProfilePage()



        ;
    }
}
