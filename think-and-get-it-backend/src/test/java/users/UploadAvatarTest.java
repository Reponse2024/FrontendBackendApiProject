package users;

import backend.constants.HttpStatus;
import backend.constants.ResponsePaths;
import backend.constants.userConstants.UserResponseMessages;
import backend.implementFlow.UserFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static spec.SpecBuilder.getRequestSpec;

public class UploadAvatarTest {

    @Test
    public void uploadAvatar() {
        Response response = new UserFlow().uploadAvatar(getRequestSpec());

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), UserResponseMessages.AVATAR_UPDATED);
    }
}
