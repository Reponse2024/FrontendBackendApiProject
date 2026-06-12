package cart;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.constants.cartConstants.CartTestData;
import backend.implementFlow.CartFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ResponseAssertions;

import static spec.SpecBuilder.getRequestSpec;

public class UpdateItemQuantityTest {

    @Test
    public void updateItemQuantity() {
        CartFlow cartFlow = new CartFlow();
        cartFlow.addItemToCart(getRequestSpec());

        String itemId = cartFlow.getFirstItemId(getRequestSpec());
        Assert.assertNotNull(itemId, ResponseMessages.ITEM_ID_SHOULD_NOT_BE_NULL);

        Response response = cartFlow.updateItemQuantity(getRequestSpec(), itemId, CartTestData.DEFAULT_QUANTITY);
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.CART_UPDATED);
    }
}
