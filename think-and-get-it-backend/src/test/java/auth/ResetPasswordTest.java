package auth;

import backend.constants.HttpStatus;
import backend.implementFlow.AuthFlow;
import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ResetPasswordTest extends BaseTest {

    @Test
    public void resetPassword() {
        Response response = new AuthFlow().resetPassword(requestSpec);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean("success"));
        Assert.assertEquals(response.jsonPath().getString("message"), "Password reset successful");
    }
}

