package search;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.constants.searchConstants.SearchTestData;
import backend.implementFlow.DynamicProductFlow;
import backend.implementFlow.SearchFlow;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ResponseAssertions;
import static spec.SpecBuilder.getRequestSpec;

public class AutocompleteSuggestionsTest {
    @Test
    public void getSearchSuggestionsSuccessfully() {
        RequestSpecification spec = getRequestSpec();
        Response response = new SearchFlow().suggestions(spec, SearchTestData.SUGGESTION_QUERY);
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.ME_SUCCESS);

        Assert.assertTrue(response.jsonPath().getList("data").size() > 0, ResponseMessages.SEARCH_NO_RESULTS);
        System.out.print("Response body"+ response.asString());
    }

    @Test
    public void getSearchSuggestionsFailsWithEmptyQuery() {
        RequestSpecification spec = getRequestSpec();
        Response response = new SearchFlow().suggestions(spec, SearchTestData.EMPTY_QUERY);
        ResponseAssertions.assertFailure(response, HttpStatus.BAD_REQUEST.code(), ResponseMessages.SEARCH_QUERY_REQUIRED);
    }
}
