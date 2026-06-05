package auth;

import backend.constants.HttpStatus;
import backend.constants.ResponsePaths;
import backend.constants.ResponseMessages;
import backend.implementFlow.AuthFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static spec.SpecBuilder.getRequestSpec;

public class RegisterTest {

    @Test
    public void registerUser() {
        Response response = new AuthFlow().register(getRequestSpec());

        Assert.assertEquals(response.getStatusCode(), HttpStatus.CREATED.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.REGISTER_SUCCESS);
        Assert.assertNotNull(response.jsonPath().getString(ResponsePaths.USER_FIRST_NAME));
        Assert.assertNotNull(response.jsonPath().getString(ResponsePaths.USER_LAST_NAME));
        Assert.assertNotNull(response.jsonPath().getString(ResponsePaths.USER_EMAIL));
    }
}
