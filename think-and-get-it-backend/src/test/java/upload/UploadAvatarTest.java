package upload;

import backend.constants.HttpStatus;
import backend.constants.ResponsePaths;
import backend.constants.userConstants.UserResponseMessages;
import backend.implementFlow.UserFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;

import static spec.SpecBuilder.getMultipartSpec;

public class UploadAvatarTest {
    @Test
    public void uploadAvatar() {
        File avatarFile = new File("src\\test\\resources\\img.png");
        Response response = new UserFlow().uploadAvatar(getMultipartSpec(), avatarFile);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), UserResponseMessages.AVATAR_UPDATED);
    }
}
