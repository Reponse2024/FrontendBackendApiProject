package users;

import backend.constants.HttpStatus;
import backend.constants.ResponsePaths;
import backend.constants.userConstants.UserResponseMessages;
import backend.implementFlow.UserFlow;
import backend.tokenManager.TokenManager;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static spec.SpecBuilder.getRequestSpec;

public class GetAddressesTest {

    @Test
    public void getAddresses() {
        Response response = new UserFlow().getAddresses(getRequestSpec());

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), UserResponseMessages.ADDRESSES_FETCHED);
    }
}
