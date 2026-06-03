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

public class LoginTest extends BaseTest {

    @Test
    public void loginUser() {
        Response response = new AuthFlow().login(requestSpec);

        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.USER_FIRST_NAME), UserData.EXISTING_USER_FIRST_NAME);
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.USER_LAST_NAME), UserData.EXISTING_USER_LAST_NAME);
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.LOGIN_SUCCESS);
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.code());
    }


}
