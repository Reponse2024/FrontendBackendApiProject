package cart;

import backend.constants.HttpStatus;
import backend.constants.ResponseMessages;
import backend.implementFlow.CartFlow;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ResponseAssertions;

import static spec.SpecBuilder.getRequestSpec;

public class SaveItemForLaterTest {

    @Test
    public void saveItemForLater() {
        CartFlow cartFlow = new CartFlow();

        cartFlow.addItemToCart(getRequestSpec());

        String itemId = cartFlow.getFirstItemId(getRequestSpec());
        Assert.assertNotNull(itemId, ResponseMessages.ITEM_ID_SHOULD_NOT_BE_NULL);

        Response response = cartFlow.saveItemForLater(getRequestSpec(), itemId);
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.ITEM_SAVED_FOR_LATER);
    }
}
