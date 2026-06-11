package cart;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.implementFlow.CartFlow;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ResponseAssertions;

import static spec.SpecBuilder.getRequestSpec;

public class ClearCartTest {

    @Test
    public void clearCart() {
        Response response = new CartFlow().clearCart(getRequestSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.CART_CLEARED);
    }
}
