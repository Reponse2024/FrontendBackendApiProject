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

public class GetProductBySlugTest {
    @Test
    public void getProductBySlugSuccess() {
        Response response = new ProductFlow().getProductBySlug(getRequestSpec(), ProductData.VALID_SLUG_SMARTPHONE);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ProductResponseMessages.MESSAGE);
    }

    @Test
    public void getProductBySlugNotFound() {
        Response response = new ProductFlow().getProductBySlug(getRequestSpec(), ProductData.INVALID_SLUG);

        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND.code());
        Assert.assertFalse(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ProductResponseMessages.PRODUCT_NOT_FOUND);
    }




}
