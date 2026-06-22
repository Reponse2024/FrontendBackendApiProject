package admin;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.constants.adminConstants.AdminTestData;
import backend.constants.searchConstants.SearchTestData;
import backend.implementFlow.AdminFlow;
import backend.tokenManager.TokenManager;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ResponseAssertions;

import static spec.SpecBuilder.getRequestSpec;

public class GetUsersTest {
    @Test
    public void getUsersSuccessfully() {
        RequestSpecification spec = getRequestSpec();
        Response response = new AdminFlow().getUsers(spec, SearchTestData.DEFAULT_PAGE, AdminTestData.SEARCH_TERM);
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.ME_SUCCESS);
        Assert.assertTrue(response.jsonPath().getList("data").size() > 0, ResponseMessages.ADMIN_NO_USERS);
        System.out.println("Response Body" + response.asString());
    }

    @Test
    public void getUsersFailsWithInvalidAuth() {
        RequestSpecification spec = getRequestSpec();
        String token = TokenManager.getAuthToken(spec);
        spec.header("Authorization", token);
        Response response = new AdminFlow().getUsers(spec, SearchTestData.DEFAULT_PAGE, AdminTestData.SEARCH_TERM);
        ResponseAssertions.assertFailure(response, HttpStatus.UNAUTHORIZED.code(), HttpStatus.UNAUTHORIZED.message());
    }

}
