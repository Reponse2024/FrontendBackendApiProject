package auth;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.constants.ResponsePaths;
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
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.PASSWORD_RESET_SUCCESS);
    }


}

