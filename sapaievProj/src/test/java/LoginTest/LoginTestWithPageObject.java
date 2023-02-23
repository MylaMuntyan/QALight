package LoginTest;

import baseTest.BaseTest;
import org.junit.Assert;
import org.junit.Test;
import pages.elements.HeaderElement;

public class LoginTestWithPageObject extends BaseTest {


    @Test
    public void validLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwerty");
        loginPage.clickOnButtonLogin();

        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
    }


    @Test
    public void invalidLogin() {
        loginPage.openLoginPage();
        loginPage.enterUserNameIntoInputLogin("qaauto");
        loginPage.enterPasswordIntoInputPassword("123456qwertwwwy");
        loginPage.clickOnButtonLogin();
        Assert.assertFalse("Button is not displayed",
                homePage.getHeaderElement().isButtonSignOutDisplayed());
        Assert.assertTrue("Button is not displayed",
                homePage.getHeaderElement().isButtonSignInDisplayed());


    }



}
