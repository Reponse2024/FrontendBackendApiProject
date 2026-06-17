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

public class UpdateOrderStatusAdminTest {
    @Test
    public void updateOrderStatusSuccessfully() {
        OrderFlow orderFlow = new OrderFlow();
        Response orderResponse = orderFlow.placeOrder(getRequestSpec());
        String orderId = orderResponse.jsonPath().getString("data.id");
        Assert.assertNotNull(orderId, ResponseMessages.ORDER_ID_CAN_NOT_BE_NULL);
        Response updateResponse = orderFlow.updateOrderStatusAdmin(
                getRequestSpec(),
                orderId,
                OrderStatusConstants.DELIVERED,
                OrderStatusConstants.DELIVERED_MESSAGE,
                OrderStatusConstants.TRACKING_NUMBER_VALID
        );
        Assert.assertEquals(updateResponse.statusCode(), HttpStatus.OK.code(), ResponseMessages.EXPECTED_200);
        Assert.assertEquals(updateResponse.jsonPath().getString("message"), ResponseMessages.ORDER_STATUS_UPDATED);
        Assert.assertEquals(updateResponse.jsonPath().getString("data.status"), OrderStatusConstants.DELIVERED, ResponseMessages.STATUS_SHOULD_BE_DELIVERED);
        Assert.assertEquals(updateResponse.jsonPath().getString("data.trackingNumber"), OrderStatusConstants.TRACKING_NUMBER_VALID);
    }
    @Test
    public void updateOrderStatusAdminFailsWithoutAuth() {
        Response response = new OrderFlow().updateOrderStatusAdminWithoutAuth(getRequestSpec());
        ResponseAssertions.assertFailure(response, HttpStatus.UNAUTHORIZED.code(), HttpStatus.UNAUTHORIZED.message());
    }
}
