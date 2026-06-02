package auth;

import backend.constants.HttpStatus;
import backend.implementFlow.AuthFlow;
import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class LoginTest extends BaseTest {

    public static String authToken;
    public static String refreshToken;

    @Test
    public void loginUser() {
 Response response = new AuthFlow().login(requestSpec);

 Assert.assertEquals(response.jsonPath().getString("data.user.firstName"), "Test");
 Assert.assertEquals(response.jsonPath().getString("data.user.lastName"), "User");
 Assert.assertEquals(response.jsonPath().getString("message"), "Login successful");
 Assert.assertTrue(response.jsonPath().getBoolean("success"));
 Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.code());

    }
}
