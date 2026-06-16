package orders;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.implementFlow.OrderFlow;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ResponseAssertions;

import static spec.SpecBuilder.getRequestSpec;

public class GetAllOrdersAdminTest {
    @Test
    public void getAllOrdersAdminSuccessfully() {
        Response response = new OrderFlow().getAllOrdersAdmin(getRequestSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.ME_SUCCESS);
    }
    @Test
    public void getAllOrdersAdminFailsWithoutAdminRole() {
        Response response = new OrderFlow().getAllOrdersAdminWithoutRole(getRequestSpec());
        ResponseAssertions.assertFailure(response, HttpStatus.FORBIDDEN.code(), HttpStatus.FORBIDDEN.message());
    }

}
