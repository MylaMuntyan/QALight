package registerTest;

import baseTest.BaseTest;
import libs.SpreadsheetData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import static pages.CommonActionsWithElements.configProperties;

@RunWith(Parameterized.class)

public class RegistrationErrorsTestWithExcel extends BaseTest {
    String userName, email, password, expectedErrors;

    public RegistrationErrorsTestWithExcel(String userName, String email, String password, String expectedErrors) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.expectedErrors = expectedErrors;
    }

    @Parameterized.Parameters
    public static Collection testData() throws IOException { //throws тому що можуть з'явитися есепшини, ми просто повідомляємо про них.
        InputStream inputStream = new FileInputStream(configProperties.DATA_FILE_PATH() + "testDataSuit.xls");

        return new SpreadsheetData(inputStream, "registrationErrors").getData(); //на вхід дати інпут стрінг, і назву аркуша екселя
    }

    @Test
    public void checkErrors() {
        loginPage
                .openLoginPage();
        loginPage
                .enterUserNameInRegistrationForm(userName)
                .enterUserEmailInRegistrationForm(email)
                .enterPasswordInRegistrationForm(password)
                .checkErrorsMessages(expectedErrors)//на вхід даємо список ерор меседжів з сайту
        ;

    }


}

