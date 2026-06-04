package products;

import backend.constants.HttpStatus;
import backend.constants.ResponsePaths;
import backend.constants.productsConstants.ProductResponseMessages;
import backend.implementFlow.ProductFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ResponseAssertions;

import static spec.SpecBuilder.getRequestSpec;

public class GetTrendingProductsTest {
    @Test
    public void getTrendingProducts() {
        Response response = new ProductFlow().getTrendingProducts(getRequestSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ProductResponseMessages.MESSAGE);
    }

}
