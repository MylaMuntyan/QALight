package InvalidRegistrationTest;

import baseTest.BaseTest;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import junitparams.naming.TestCaseName;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class registrationErrorTest extends BaseTest {
    final static String ERROR_USERNAME = "Username must be at least 3 characters.";
    final static String ERROR_EMAIL = "You must provide a valid email address.";
    final static String ERROR_PASSWORD = "Password must be at least 12 characters.";
    final static String ERROR_ALREADY_EXIST = "That username is already taken.";
    final static String ERROR_PASSWORD_TOO_BIG = "Password cannot exceed 50 characters.";
    final static String ERROR_WRONG_LANGUAGE = "Username can only contain letters and numbers.";
    final static String SEMICOLON = ";";
    final static String COMMA = ",";
    final static String SHORT_USER_NAME = "tr";
    String expectedErrors = ERROR_USERNAME+COMMA+ERROR_EMAIL+COMMA+ERROR_PASSWORD;
    @Test
    @Parameters(method = "provideParameters")
    @TestCaseName("registrationErrors : login = {0}, email = {1}, password = {2}")
    public void checkErrors(String usrName, String email, String password, String expectedErrors){
        loginPage.openLoginPage()
                .enterUserNameToRegister(usrName)
                .enterEmailToRegister(email)
                .enterPasswordToRegister(password)
                .checkRegistrationErrorsMessages(expectedErrors)
        ;
    }
    public static Object[][] provideParameters() {
        return new Object[][] {
                new Object[] {"tr", "dfg", "re", ERROR_USERNAME + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD},
                new Object[] {"tr", "df@ee.com", "re", ERROR_USERNAME + COMMA + ERROR_PASSWORD},
                new Object[] {"апци", "dfg", "re", ERROR_WRONG_LANGUAGE + COMMA + ERROR_EMAIL + COMMA + ERROR_PASSWORD},
                new Object[] {"trwef", "df@ee.com", "Uttellusnisi,tristiquevelturpissitamet," +
                                                              "accumsanporttitorligulaPraesentsemipsum," +
                                                              "feugiatnonrisusut,mollismolestiearcu.Quisquehoncusorcinec" +
                                                              " felispulvinaraconsequatarcublanditSedineugiatleoauctorsodales" +
                                                              "nibh.InsitamethendrerittellusNaminrisussitametodioeuismodporttitor." +
                                                              " MaecenasmolestieporttitortellusutultricesnislconsecteturacNammales" +
                                                              "uadadictumdiamvitaeconvallis.Maecenasmolestieporttitortellusutultrices" +
                                                              "nislconsecteturacNammalesMaecenasmolestieporttitortellusutultricesnislconsec" +
                                                              "teturacNammalesMaecenasmolestieporttitortellusutultricesnislconsecteturacNam" +
                                                              "uracNammalesMaecenasmolestieporttitortellusutultricesnislconsectetur" +
                                                              "acNamm", ERROR_PASSWORD_TOO_BIG},
        };
    }

}
