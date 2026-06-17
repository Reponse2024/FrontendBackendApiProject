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
        Response response = new OrderFlow().cancelOrderNew(getRequestSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.ORDER_CANCELLED);
    }

    @Test
    public void cancelOrderFailsAlreadyCancelled() {
        OrderFlow orderFlow = new OrderFlow();
        Response orderResponse = orderFlow.placeOrder(getRequestSpec());
        String orderId = orderResponse.jsonPath().getString("data.id");
        orderFlow.cancelOrder(getRequestSpec(), orderId);
        Response response = orderFlow.cancelOrder(getRequestSpec(), orderId);
        ResponseAssertions.assertFailure(response, HttpStatus.BAD_REQUEST.code(), ResponseMessages.ORDER_ALREADY_CANCELLED);
    }
}
