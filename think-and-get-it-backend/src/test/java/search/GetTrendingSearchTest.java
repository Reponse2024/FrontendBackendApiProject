package search;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.constants.searchConstants.SearchTestData;
import backend.implementFlow.SearchFlow;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ResponseAssertions;

import static spec.SpecBuilder.getRequestSpec;

public class GetTrendingSearchTest {
    @Test
    public void getTrendingSearchesSuccessfully() {
        RequestSpecification spec = getRequestSpec();
        Response response = new SearchFlow().trending(spec);
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.ME_SUCCESS);
        Assert.assertTrue(response.jsonPath().getList("data").size() > 0, ResponseMessages.SEARCH_NO_RESULTS);
        System.out.print("Response body"+ response.asString());
    }

    @Test
    public void getTrendingSearchesPassWithInvalidAuth() {
        RequestSpecification spec = getRequestSpec();
        spec.header("Authorization", SearchTestData.INVALID_TOKEN);
        Response response = new SearchFlow().trending(spec);
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.ME_SUCCESS);
        System.out.print("Response body"+ response.asString());
    }
}
