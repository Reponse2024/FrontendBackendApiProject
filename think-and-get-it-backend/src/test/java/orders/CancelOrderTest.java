package orders;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.implementFlow.OrderFlow;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ResponseAssertions;

import static spec.SpecBuilder.getRequestSpec;

public class CancelOrderTest {
    @Test
    public void cancelOrderSuccessfully() {
        Response response = new OrderFlow().cancelOrder(getRequestSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.ORDER_CANCELLED);
    }
    @Test
    public void cancelOrderFailsAlreadyCancelled() {
        Response response = new OrderFlow().cancelOrder(getRequestSpec());
        Response response2 = new OrderFlow().cancelOrder(getRequestSpec());
        ResponseAssertions.assertFailure(response2, HttpStatus.BAD_REQUEST.code(), ResponseMessages.ORDER_ALREADY_CANCELLED);
    }

}
