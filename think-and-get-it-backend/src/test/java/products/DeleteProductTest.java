package products;

import backend.constants.HttpStatus;
import backend.constants.ResponsePaths;
import backend.constants.productsConstants.ProductResponseMessages;
import backend.implementFlow.ProductFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static spec.SpecBuilder.getRequestSpec;

public class DeleteProductTest {
    @Test
    public void deleteProduct() {
        Response response = new ProductFlow().deleteProduct(getRequestSpec());

        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK.code());
        Assert.assertTrue(response.jsonPath().getBoolean(ResponsePaths.SUCCESS));
        Assert.assertEquals(response.jsonPath().getString(ResponsePaths.MESSAGE), ProductResponseMessages.PRODUCT_DELETED);
    }

}
