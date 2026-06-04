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

public class GetRelatedProductsTest {
    @Test
    public void getRelatedProductsSuccess() {
        Response response = new ProductFlow().getRelatedProducts(getRequestSpec(), ProductData.VALID_ID_SMARTPHONE);
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ProductResponseMessages.MESSAGE);
    }

    @Test
    public void getRelatedProductsNotFound() {
        Response response = new ProductFlow().getRelatedProducts(getRequestSpec(), ProductData.INVALID_ID);
        ResponseAssertions.assertFailure(response, HttpStatus.NOT_FOUND.code(), ProductResponseMessages.PRODUCT_NOT_FOUND);
    }



}
