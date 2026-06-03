package auth;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.constants.ResponsePaths;
import backend.implementFlow.AuthFlow;
import base.BaseTest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RefreshTest extends BaseTest {

    @Test
    public void refreshToken() {
        Response response = new AuthFlow().getRefreshedToken(requestSpec);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.REFRESH_SUCCESS);
        Assert.assertNotNull(response.jsonPath().getString(ResponsePaths.TOKEN));
    }
}
