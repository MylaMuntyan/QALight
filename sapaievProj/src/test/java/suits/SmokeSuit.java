package suits;
import LoginTest.LoginTestWithPageObject;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import postTest.CreatePostTest;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginTestWithPageObject.class,
        CreatePostTest.class
})

public class SmokeSuit {

}