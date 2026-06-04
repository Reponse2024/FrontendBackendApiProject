package products;

import backend.constants.HttpStatus;
import backend.constants.productsConstants.ProductResponseMessages;
import backend.implementFlow.ProductFlow;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import utils.ResponseAssertions;

import static spec.SpecBuilder.getRequestSpec;

public class UploadProductImagesTest {
    @Test
    public void uploadProductImages() {
        Response response = new ProductFlow().uploadProductImages(getRequestSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.CREATED.code(), ProductResponseMessages.IMAGES_UPLOADED);
    }


}
