package auth;

import backend.constants.ApiEndpoints;
import backend.constants.HttpStatus;
import backend.implementFlow.AuthFlow;
import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    @Test
    public void registerUser() {

    Response response = new AuthFlow().register(requestSpec);

    Assert.assertEquals(response.jsonPath().getString("data.user.firstName"), "Diemme");
    Assert.assertEquals(response.jsonPath().getString("data.user.lastName"), "Merci");
    Assert.assertTrue(response.jsonPath().getBoolean("success"));
    Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED.code());
    }
}
