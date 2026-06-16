package orders;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.implementFlow.OrderFlow;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ResponseAssertions;
import static spec.SpecBuilder.getRequestSpec;

public class PlaceOrderTest {

    @Test
    public void placeOrderSuccessfully() {
        Response response = new OrderFlow().placeOrder(getRequestSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.CREATED.code(), ResponseMessages.ORDER_PLACED);
    }
    @Test
    public void placeOrderFailsWithoutCartItems() {
        Response response = new OrderFlow().placeOrderWithoutCart(getRequestSpec());
        ResponseAssertions.assertFailure(response, HttpStatus.BAD_REQUEST.code(), ResponseMessages.CART_EMPTY);
    }
}
