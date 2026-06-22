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

public class FullTextSearchTest {
    @Test
    public void searchProductsSuccessfully() {
        RequestSpecification spec = getRequestSpec();

        Response response = new SearchFlow().search(
                spec,
                SearchTestData.VALID_QUERY,
                SearchTestData.DEFAULT_PAGE,
                SearchTestData.DEFAULT_LIMIT,
                SearchTestData.CATEGORY,
                SearchTestData.MIN_PRICE,
                SearchTestData.MAX_PRICE,
                SearchTestData.SORT_PRICE_ASC
        );
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.ME_SUCCESS);
        Assert.assertTrue(response.jsonPath().getList("data").size() > 0, ResponseMessages.SEARCH_NO_RESULTS);
        System.out.print("Response body"+ response.asString());
    }

    @Test
    public void searchProductsFailsWithNullArguments() {
        RequestSpecification spec = getRequestSpec();
        Response response = new SearchFlow().search(
                spec,
                null,
                null,
                null,
                null,
                null,
                null,
                null
        );
        System.out.print("Response body"+ response.asString());
    }
}
