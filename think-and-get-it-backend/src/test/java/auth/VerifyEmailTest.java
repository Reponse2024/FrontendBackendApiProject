package auth;

import backend.constants.authConstants.AuthData;
import backend.constants.HttpStatus;
import backend.constants.ResponsePaths;
import backend.constants.ResponseMessages;
import backend.implementFlow.AuthFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static spec.SpecBuilder.getRequestSpec;

public class VerifyEmailTest {

    @Test
    public void verifyEmailWithValidToken() {
        Response response = new AuthFlow().verifyEmail(getRequestSpec());

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.EMAIL_VERIFIED);
        Assert.assertNotNull(response.jsonPath().getString(ResponsePaths.USER_EMAIL));
        Assert.assertNotNull(response.jsonPath().getString(ResponsePaths.USER_FIRST_NAME));
        Assert.assertNotNull(response.jsonPath().getString(ResponsePaths.USER_LAST_NAME));
    }

    @Test
    public void verifyEmailWithInvalidToken() {
        Response response = new AuthFlow().verifyEmailWithInvalidToken(getRequestSpec(), AuthData.INVALID_TOKEN );

        Assert.assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST.code());
        Assert.assertFalse(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.VERIFY_EMAIL_FAIL);
    }
}
