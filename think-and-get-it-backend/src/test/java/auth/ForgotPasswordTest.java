package auth;

import backend.constants.HttpStatus;
import backend.implementFlow.AuthFlow;
import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ForgotPasswordTest extends BaseTest {

    @Test
    public void forgotPassword() {
        Response response = new AuthFlow().forgotPassword(requestSpec);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "If an account with that email exists, a reset link has been sent.");
    }
}
