package orders;

import backend.constants.ApiEndpoints;
import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.implementFlow.OrderFlow;
import backend.tokenManager.TokenManager;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ResponseAssertions;

import static io.restassured.RestAssured.given;
import static spec.SpecBuilder.getRequestSpec;

public class GetSingleOrderTest {
    @Test
    public void getSingleOrderSuccessfully() {
        Response response = new OrderFlow().getSingleOrder(getRequestSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.ME_SUCCESS);
    }

    @Test
    public void getSingleOrderFailsWithInvalidId() {
        Response response = new OrderFlow().getSingleOrderWithInvalidId(getRequestSpec());
        ResponseAssertions.assertFailure(response, HttpStatus.NOT_FOUND.code(), ResponseMessages.ORDER_NOT_FOUND);
    }
}
