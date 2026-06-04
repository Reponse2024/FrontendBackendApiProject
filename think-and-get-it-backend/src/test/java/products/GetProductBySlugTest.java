package products;

import backend.constants.HttpStatus;
import backend.constants.ResponsePaths;
import backend.constants.productsConstants.ProductData;
import backend.constants.productsConstants.ProductResponseMessages;
import backend.implementFlow.ProductFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ResponseAssertions;

import static spec.SpecBuilder.getRequestSpec;

public class GetProductBySlugTest {
    @Test
    public void getProductBySlugSuccess() {
        Response response = new ProductFlow().getProductBySlug(getRequestSpec(), ProductData.VALID_SLUG_SMARTPHONE);
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ProductResponseMessages.MESSAGE);
    }

    @Test
    public void getProductBySlugNotFound() {
        Response response = new ProductFlow().getProductBySlug(getRequestSpec(), ProductData.INVALID_SLUG);
        ResponseAssertions.assertFailure(response, HttpStatus.NOT_FOUND.code(), ProductResponseMessages.PRODUCT_NOT_FOUND);
    }
}
