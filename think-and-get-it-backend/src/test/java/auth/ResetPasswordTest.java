package auth;

import backend.constants.HttpStatus;
import backend.constants.ResponsePaths;
import backend.constants.ResponseMessages;
import backend.implementFlow.AuthFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static spec.SpecBuilder.getRequestSpec;

public class ResetPasswordTest {

    @Test
    public void resetPasswordWithValidToken() {
        Response response = new AuthFlow().resetPassword(getRequestSpec());

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.PASSWORD_RESET_SUCCESS);
    }

    @Test
    public void resetPasswordWithInvalidToken() {
        Response response = new AuthFlow().resetPassword(getRequestSpec());

        Assert.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST.code());
        Assert.assertFalse(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.PASSWORD_RESET_FAIL);
    }
}
