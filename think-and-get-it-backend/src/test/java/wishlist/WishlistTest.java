package wishlist;

import backend.implementFlow.WishlistFlow;
import backend.constants.ResponseMessages;
import backend.constants.HttpStatus;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import spec.SpecBuilder;
import utils.ResponseAssertions;

import static spec.SpecBuilder.getRequestSpec;

public class WishlistTest {

    private final WishlistFlow wishlistFlow = new WishlistFlow();

    @Test
    public void getWishlistSuccessfully() {
        Response response = wishlistFlow.getWishlist(getRequestSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.WISHLIST_FETCHED);
    }

    @Test
    public void addProductToWishlistSuccessfully() {
        Response response = wishlistFlow.addProductToWishlist(getRequestSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.CREATED.code(), ResponseMessages.WISHLIST_ADDED);
    }

    @Test
    public void addProductToWishlistFailsIfAlreadyExists() {
        wishlistFlow.addProductToWishlist(getRequestSpec());
        Response response = wishlistFlow.addProductToWishlist(getRequestSpec());
        ResponseAssertions.assertFailure(response, HttpStatus.CONFLICT.code(), ResponseMessages.WISHLIST_ALREADY_EXISTS);
    }

    @Test
    public void removeProductFromWishlistSuccessfully() {
        wishlistFlow.addProductToWishlist(getRequestSpec());
        Response response = wishlistFlow.removeProductFromWishlist(getRequestSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.WISHLIST_REMOVED);
    }

    @Test
    public void moveWishlistItemToCartSuccessfully() {
        wishlistFlow.addProductToWishlist(getRequestSpec());
        Response response = wishlistFlow.moveWishlistItemToCart(getRequestSpec());
        ResponseAssertions.assertSuccess(response, HttpStatus.OK.code(), ResponseMessages.WISHLIST_MOVED_TO_CART);
    }

    @Test
    public void moveWishlistItemToCartFailsIfNotInWishlist() {
        wishlistFlow.removeProductFromWishlist(getRequestSpec());
        Response response = wishlistFlow.moveWishlistItemToCart(getRequestSpec());
        ResponseAssertions.assertFailure(response, HttpStatus.INTERNAL_SERVER_ERROR.code(), ResponseMessages.INTERNAL_ERROR);
    }

}
