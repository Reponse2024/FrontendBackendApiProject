package cart;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.implementFlow.CartFlow;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ResponseAssertions;

import static spec.SpecBuilder.getRequestSpec;

public class GetCurrentCartTest {

    @Test
    public void getCurrentCart() {
        Response response = new CartFlow().getCart(getRequestSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.ME_SUCCESS);
    }
}
