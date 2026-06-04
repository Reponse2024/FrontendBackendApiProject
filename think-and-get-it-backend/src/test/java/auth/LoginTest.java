package auth;

import backend.constants.HttpStatus;
import backend.constants.ResponsePaths;
import backend.constants.ResponseMessages;
import backend.constants.UserData;
import backend.implementFlow.AuthFlow;
import spec.SpecBuilder;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static spec.SpecBuilder.getRequestSpec;

public class LoginTest {

    @Test
    public void loginUser() {
        Response response = new AuthFlow().login(getRequestSpec());

        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.USER_FIRST_NAME), UserData.EXISTING_USER_FIRST_NAME);
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.USER_LAST_NAME), UserData.EXISTING_USER_LAST_NAME);
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.LOGIN_SUCCESS);
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.code());
    }
}
