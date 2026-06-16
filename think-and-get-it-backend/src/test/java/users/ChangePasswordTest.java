package users;

import backend.constants.HttpStatus;
import backend.constants.ResponsePaths;
import backend.constants.userConstants.UserResponseMessages;
import backend.constants.userConstants.UsersData;
import backend.implementFlow.UserFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static spec.SpecBuilder.getRequestSpec;

public class ChangePasswordTest {

    @Test
    public void changePassword() {
        Response response = new UserFlow().changePassword(getRequestSpec(), UsersData.CURRENT_PASSWORD, UsersData.NEW_PASSWORD);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), UserResponseMessages.PASSWORD_CHANGED);
    }
}
