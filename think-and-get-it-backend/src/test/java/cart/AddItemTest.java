package cart;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.constants.ResponsePaths;
import backend.constants.cartConstants.CartTestData;
import backend.implementFlow.CartFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ResponseAssertions;

import static spec.SpecBuilder.getRequestSpec;

public class AddItemTest {

    @Test
    public void addItemToCart() {
        Response response = new CartFlow().addItemToCart(getRequestSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.ITEM_ADDED);
        Assert.assertNotNull(response.jsonPath().getMap(CartTestData.DATA_MAP));
    }
}
