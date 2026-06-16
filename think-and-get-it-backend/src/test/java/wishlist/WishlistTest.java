package wishlist;

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

        new WishlistFlow().addProductToWishlist(spec);
        Response response = new WishlistFlow().getWishlist(spec);

        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.WISHLIST_FETCHED);
        List<Object> items = response.jsonPath().getList("data");
        Assert.assertTrue(items != null && items.size() > 0, "Wishlist should contain at least one product");
    }


    @Test
    public void addProductToWishlistSuccessfully() {
        Response response =new WishlistFlow().addProductToWishlist(getRequestSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.CREATED.code(), ResponseMessages.WISHLIST_ADDED);
    }

    @Test
    public void addProductToWishlistFailsIfAlreadyExists() {
        new WishlistFlow().addProductToWishlist(getRequestSpec());
        Response response = new WishlistFlow().addProductToWishlist(getRequestSpec());
        ResponseAssertions.assertFailure(response, HttpStatus.CONFLICT.code(), ResponseMessages.WISHLIST_ALREADY_EXISTS);
    }

    @Test
    public void removeProductFromWishlistSuccessfully() {
        new WishlistFlow().addProductToWishlist(getRequestSpec());
        Response response =new WishlistFlow().removeProductFromWishlist(getRequestSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.WISHLIST_REMOVED);
    }

    @Test
    public void moveWishlistItemToCartSuccessfully() {
        new WishlistFlow().addProductToWishlist(getRequestSpec());
        Response response = new WishlistFlow().moveWishlistItemToCart(getRequestSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.WISHLIST_MOVED_TO_CART);
    }

    @Test
    public void moveWishlistItemToCartFailsIfNotInWishlist() {
        new WishlistFlow().removeProductFromWishlist(getRequestSpec());
        Response response = new WishlistFlow().moveWishlistItemToCart(getRequestSpec());
        ResponseAssertions.assertFailure(response, HttpStatus.INTERNAL_SERVER_ERROR.code(), ResponseMessages.INTERNAL_ERROR);
    }

}
