package orders;

import backend.constants.HttpStatus;
import backend.constants.OrderConstants.OrderStatusConstants;
import backend.constants.ResponseMessages;
import backend.implementFlow.OrderFlow;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ResponseAssertions;

import static spec.SpecBuilder.getRequestSpec;

public class UpdateOrderStatusAdminTest {

    @Test
    public void updateOrderStatusAdminSuccessfully() {
        Response response = new OrderFlow().updateOrderStatusAdmin(
                getRequestSpec(),
                OrderStatusConstants.DELIVERED,
                OrderStatusConstants.DELIVERED_MESSAGE,
                OrderStatusConstants.TRACKING_NUMBER_VALID
        );
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.ORDER_STATUS_UPDATED);
    }

    @Test
    public void updateOrderStatusAdminFailsWithoutAuth() {
        Response response = new OrderFlow().updateOrderStatusAdminWithoutAuth(getRequestSpec());
        ResponseAssertions.assertFailure(response, HttpStatus.UNAUTHORIZED.code(), HttpStatus.UNAUTHORIZED.message());
    }
}
