package wishlist;

import backend.implementFlow.DynamicProductFlow;
import backend.implementFlow.WishlistFlow;
import backend.constants.ResponseMessages;
import backend.constants.HttpStatus;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;
import spec.SpecBuilder;
import utils.ResponseAssertions;

import java.util.List;

import static spec.SpecBuilder.getRequestSpec;

public class WishlistTest {

    @Test
    public void getWishlistAfterAddingProduct() {
        RequestSpecification spec = SpecBuilder.getRequestSpec();
        String productId = new DynamicProductFlow().getProductId(spec);
        new WishlistFlow().addProductToWishlist(spec, productId);
        Response response = new WishlistFlow().getWishlist(spec);
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.WISHLIST_FETCHED);
        List<Object> items = response.jsonPath().getList("data");
        Assert.assertTrue(items != null && items.size() > 0, ResponseMessages.AT_LEAST_ONE_ITEM);
    }

    @Test
    public void addProductToWishlistSuccessfully() {
        String productId = new DynamicProductFlow().getProductId(getRequestSpec());
        Response response = new WishlistFlow().addProductToWishlist(getRequestSpec(), productId);
        ResponseAssertions.assertSuccess(response, HttpStatus.CREATED.code(), ResponseMessages.WISHLIST_ADDED);
    }

    @Test
    public void addProductToWishlistFailsIfAlreadyExists() {
        String productId = new DynamicProductFlow().getProductId(getRequestSpec());
        new WishlistFlow().addProductToWishlist(getRequestSpec(), productId);
        Response response = new WishlistFlow().addProductToWishlist(getRequestSpec(), productId);
        ResponseAssertions.assertFailure(response, HttpStatus.CONFLICT.code(), ResponseMessages.WISHLIST_ALREADY_EXISTS);
    }

    @Test
    public void removeProductFromWishlistSuccessfully() {
        String productId = new DynamicProductFlow().getProductId(getRequestSpec());
        new WishlistFlow().addProductToWishlist(getRequestSpec(), productId);
        Response response = new WishlistFlow().removeProductFromWishlist(getRequestSpec(), productId);
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.WISHLIST_REMOVED);
    }

    @Test
    public void moveWishlistItemToCartSuccessfully() {
        Response response = new WishlistFlow().moveItemToCartWhenPresent(getRequestSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.WISHLIST_MOVED_TO_CART);
    }

    @Test
    public void moveWishlistItemToCartFailsIfNotInWishlist() {
        Response response = new WishlistFlow().moveItemToCartWhenAbsent(getRequestSpec());
        ResponseAssertions.assertFailure(response, HttpStatus.BAD_REQUEST.code(), ResponseMessages.WISHLIST_REMOVED);
    }


}
