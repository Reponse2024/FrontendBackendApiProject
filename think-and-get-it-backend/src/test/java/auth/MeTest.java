package auth;

import backend.configManager.ConfigManager;
import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.constants.ResponsePaths;
import backend.implementFlow.AuthFlow;
import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MeTest extends BaseTest {

    @Test
    public void getCurrentUser() {
        Response response = new AuthFlow().getCurrentUser(requestSpec);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.code());
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.ME_SUCCESS);
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertNotNull(response.jsonPath().getString(ResponsePaths.USER_EMAIL));
    }

}
