package products;

import backend.constants.HttpStatus;
import backend.constants.ResponsePaths;
import backend.constants.productsConstants.ProductData;
import backend.constants.productsConstants.ProductResponseMessages;
import backend.implementFlow.ProductFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static spec.SpecBuilder.getRequestSpec;

public class GetRelatedProductsTest {
    @Test
    public void getRelatedProductsSuccess() {
        Response response = new ProductFlow().getRelatedProducts(getRequestSpec(), ProductData.VALID_ID_SMARTPHONE);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ProductResponseMessages.MESSAGE);
    }

    @Test
    public void getRelatedProductsNotFound() {
        Response response = new ProductFlow().getRelatedProducts(getRequestSpec(), ProductData.INVALID_ID);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND.code());
        Assert.assertFalse(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ProductResponseMessages.PRODUCT_NOT_FOUND);
    }


}
