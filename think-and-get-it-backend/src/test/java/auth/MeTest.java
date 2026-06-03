package auth;

import backend.constants.HttpStatus;
import backend.constants.ResponsePaths;
import backend.constants.ResponseMessages;
import backend.implementFlow.AuthFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static spec.SpecBuilder.getRequestSpec;

public class MeTest {

    @Test
    public void getCurrentUser() {
        Response response = new AuthFlow().getCurrentUser(getRequestSpec());

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.code());
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.ME_SUCCESS);
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertNotNull(response.jsonPath().getString(ResponsePaths.USER_EMAIL));
    }
}
