package orders;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.implementFlow.OrderFlow;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ResponseAssertions;
import static spec.SpecBuilder.getRequestSpec;

public class GetUserOrdersTest {
    @Test
    public void getUserOrdersSuccessfully() {
        new OrderFlow().placeOrder(getRequestSpec());
        Response response = new OrderFlow().getUserOrders(getRequestSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.ME_SUCCESS);
    }

    @Test
    public void getUserOrdersFailsWithoutAuth() {
        Response response = new OrderFlow().getUserOrdersWithoutAuth(getRequestSpec());
        ResponseAssertions.assertFailure(response, HttpStatus.UNAUTHORIZED.code(), HttpStatus.UNAUTHORIZED.message());
    }
}
