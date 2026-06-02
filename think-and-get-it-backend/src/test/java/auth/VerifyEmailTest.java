package auth;

import backend.constants.HttpStatus;
import backend.implementFlow.AuthFlow;
import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyEmailTest extends BaseTest {
    @Test
    public void VerifyEmail() {
        Response response = new AuthFlow().verifyEmail(requestSpec);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Email verified successfully");
    }
}
