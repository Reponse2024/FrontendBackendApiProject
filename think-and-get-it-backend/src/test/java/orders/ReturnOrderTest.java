package orders;

import backend.constants.HttpStatus;
import backend.constants.OrderConstants.OrderStatusConstants;
import backend.constants.ResponseMessages;
import backend.implementFlow.OrderFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ResponseAssertions;

import static spec.SpecBuilder.getRequestSpec;

public class ReturnOrderTest {

    OrderFlow orderFlow = new OrderFlow();

    @Test
    public void returnOrderSuccessfully() {
        Response orderResponse = orderFlow.placeOrder(getRequestSpec());
        String orderId = orderResponse.jsonPath().getString("data.id");
        Assert.assertNotNull(orderId, "Order ID should not be null");
        orderFlow.updateOrderStatusAdmin(
                getRequestSpec(),
                orderId,
                OrderStatusConstants.DELIVERED,
                OrderStatusConstants.DELIVERED_MESSAGE,
                OrderStatusConstants.TRACKING_NUMBER_VALID
        );
        Response response = orderFlow.returnOrder(getRequestSpec(), orderId);
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.ORDER_RETURN_REQUESTED);
        Assert.assertEquals(response.jsonPath().getString("data.status"), OrderStatusConstants.RETURNED);
    }

    @Test
    public void returnOrderFailsIfNotDelivered() {
        Response orderResponse = orderFlow.placeOrder(getRequestSpec());
        String orderId = orderResponse.jsonPath().getString("data.id");
        Assert.assertNotNull(orderId, "Order ID should not be null");
        Response response = orderFlow.returnOrder(getRequestSpec(), orderId);
        ResponseAssertions.assertFailure(response, HttpStatus.BAD_REQUEST.code(), ResponseMessages.ORDER_RETURN_WARNING);
    }

    @Test
    public void returnOrderFailsIfAlreadyReturned() {
        Response orderResponse = orderFlow.placeOrder(getRequestSpec());
        String orderId = orderResponse.jsonPath().getString("data.id");
        orderFlow.updateOrderStatusAdmin(
                getRequestSpec(),
                orderId,
                OrderStatusConstants.DELIVERED,
                OrderStatusConstants.DELIVERED_MESSAGE,
                OrderStatusConstants.TRACKING_NUMBER_VALID
        );
        orderFlow.returnOrder(getRequestSpec(), orderId);
        Response response = orderFlow.returnOrder(getRequestSpec(), orderId);
        ResponseAssertions.assertFailure(response, HttpStatus.BAD_REQUEST.code(), ResponseMessages.ORDER_RETURN_WARNING);
    }
}
