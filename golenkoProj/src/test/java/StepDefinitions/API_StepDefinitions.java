package StepDefinitions;

import api.APIHelper;
import cucumber.api.java.en.Given;
import libs.TestData;

public class API_StepDefinitions {
    final String DEFAULT = "default";
    private final APIHelper apiHelper = new APIHelper();

    @Given("^Create (\\d+) new posts via API for '(.*)' user and '(.*)' password$")
    public void createNewPostsViaAPIForDefaultUserAndDefaultPassword(int numberOfPosts, String userName, String password) {
        if (DEFAULT.equalsIgnoreCase(userName)) {
            userName = TestData.VALID_LOGIN;
        }
        if (DEFAULT.equalsIgnoreCase(password)) {
            password = TestData.VALID_PASSWORD;
        }
        for (int i = 0; i < numberOfPosts; i++) {
            apiHelper.createPost(userName, password, "Post from API Golenko " + (i + 1));

        }
    }
}
