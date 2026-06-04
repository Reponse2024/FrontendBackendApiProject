package auth;

import backend.constants.HttpStatus;
import backend.constants.ResponsePaths;
import backend.constants.ResponseMessages;
import backend.implementFlow.AuthFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static spec.SpecBuilder.getRequestSpec;

public class RefreshTest {

    @Test
    public void refreshToken() {
        Response response = new AuthFlow().getRefreshedToken(getRequestSpec());

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ResponseMessages.REFRESH_SUCCESS);
        Assert.assertNotNull(response.jsonPath().getString(ResponsePaths.TOKEN));
    }
}
