package admin;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.implementFlow.AdminFlow;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;
import utils.ResponseAssertions;

import static spec.SpecBuilder.getRequestSpec;

public class GetDashboardTest {
    @Test
    public void getDashboardSuccessfully() {
        RequestSpecification spec = getRequestSpec();
        Response response = new AdminFlow().getDashboard(spec);
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.ME_SUCCESS);
        System.out.println("Response Body: " + response.asString());
    }

    @Test
    public void getDashboardFailsWithInvalidAuth() {
        RequestSpecification spec = getRequestSpec();
        spec.header("Authorization", "Bearer invalid_token");
        Response response = new AdminFlow().getDashboard(spec);
        ResponseAssertions.assertFailure(response, HttpStatus.UNAUTHORIZED.code(), ResponseMessages.INVALID_TOKEN);
    }

}
