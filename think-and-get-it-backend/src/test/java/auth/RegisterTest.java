package auth;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.constants.ResponsePaths;
import backend.constants.UserData;
import backend.implementFlow.AuthFlow;
import base.BaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterTest extends BaseTest {

    @Test
    public void registerUser() {
        Response response = new AuthFlow().register(requestSpec);

        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.USER_FIRST_NAME), UserData.NEW_USER_FIRST_NAME);
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.USER_LAST_NAME), UserData.NEW_USER_LAST_NAME);
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.REGISTER_SUCCESS);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED.code());
    }


}
