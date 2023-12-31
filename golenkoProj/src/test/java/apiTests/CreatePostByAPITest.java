package apiTests;

import api.APIHelper;
import api.EndPoints;
import api.dto.requestDto.CreatePostDTO;
import api.dto.responseDto.AuthorDTO;
import api.dto.responseDto.PostDTO;
import io.restassured.http.ContentType;
import org.apache.log4j.Logger;
import org.assertj.core.api.SoftAssertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class CreatePostByAPITest {
    APIHelper apiHelper = new APIHelper();
    Logger logger = Logger.getLogger(getClass());

    @Before
    public void deleteAllPostsByUser(){
        apiHelper.deletePostsTillPresent();
    }

    @Test
    public void createPostByApi() {
        String token = apiHelper.getToken();

        CreatePostDTO createPostDTO = CreatePostDTO.builder()
                .title("New post from API Nataliia")
                .body("post body")
                .select1("One Person")
                .uniquePost("yes")
                .token(token)
                .build();

        String response =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .body(createPostDTO)
                        .when()
                        .post(EndPoints.CREATE_POST)
                        .then()
                        .statusCode(200)
                        .log().all()
                        .extract().response().getBody().asString();

        Assert.assertEquals("Message", "\"Congrats.\"", response);

        PostDTO[] actualPosts = apiHelper.getAllPostsByUser();

        Assert.assertEquals("Number of posts ", 1, actualPosts.length);

        PostDTO[] expectedPostsDto = {
                PostDTO.builder()
                        .title(createPostDTO.getTitle())
                        .body(createPostDTO.getBody())
                        .select1(createPostDTO.getSelect1())
                        .uniquePost(createPostDTO.getUniquePost())
                        .isVisitorOwner(false)
                        .author(AuthorDTO.builder()
                                .username(APIHelper.USER_NAME).build())
                        .build()
        };

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(actualPosts)
                        .usingRecursiveComparison()
                                .ignoringFields("id","createdDate","author.avatar")
                                        .isEqualTo(expectedPostsDto);
        softAssertions.assertAll();

    }
}
