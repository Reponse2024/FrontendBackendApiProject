package orders;

import backend.constants.HttpStatus;
import backend.constants.OrderConstants.OrderStatusConstants;
import backend.constants.ResponseMessages;
import backend.implementFlow.OrderFlow;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ResponseAssertions;

import static spec.SpecBuilder.getRequestSpec;

public class ReturnOrderTest {
    @Test
    public void returnOrderSuccessfully() {
        OrderFlow orderFlow = new OrderFlow();
        orderFlow.placeOrder(getRequestSpec());
        orderFlow.updateOrderStatusAdmin(
                getRequestSpec(),
                OrderStatusConstants.DELIVERED,
                OrderStatusConstants.DELIVERED_MESSAGE,
                OrderStatusConstants.TRACKING_NUMBER_VALID
        );
        Response response = orderFlow.returnOrder(getRequestSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.ORDER_RETURN_REQUESTED);
    }

    @Test
    public void returnOrderFailsWithoutReason() {
        OrderFlow orderFlow = new OrderFlow();
        orderFlow.placeOrder(getRequestSpec());
        orderFlow.updateOrderStatusAdmin(
                getRequestSpec(),
                OrderStatusConstants.DELIVERED,
                OrderStatusConstants.DELIVERED_MESSAGE,
                OrderStatusConstants.TRACKING_NUMBER_VALID
        );
        Response response = orderFlow.returnOrderWithoutReason(getRequestSpec());
        ResponseAssertions.assertFailure(response, HttpStatus.BAD_REQUEST.code(), ResponseMessages.REASON_REQUIRED);
    }
}
