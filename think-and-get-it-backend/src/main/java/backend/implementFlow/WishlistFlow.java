package backend.implementFlow;

import backend.constants.ApiEndpoints;
import backend.constants.reviewsConstants.ReviewsTestData;
import backend.tokenManager.TokenManager;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class WishlistFlow {

    public Response getWishlist(RequestSpecification requestSpec) {
        String token = TokenManager.getAuthToken(requestSpec);

        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .get(ApiEndpoints.WISHLIST)
                .then().extract().response();
    }

    public Response addProductToWishlist(RequestSpecification requestSpec) {
        String token = TokenManager.getAuthToken(requestSpec);

        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .pathParam("productId", ReviewsTestData.DEFAULT_PRODUCT_ID)
                .contentType(ContentType.JSON)
                .post(ApiEndpoints.WISHLIST_PRODUCT)
                .then().extract().response();
    }

    public Response removeProductFromWishlist(RequestSpecification requestSpec) {
        String token = TokenManager.getAuthToken(requestSpec);

        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .pathParam("productId", ReviewsTestData.DEFAULT_PRODUCT_ID)
                .delete(ApiEndpoints.WISHLIST_PRODUCT)
                .then().extract().response();
    }

    public Response moveWishlistItemToCart(RequestSpecification requestSpec, String productId) {
        String token = TokenManager.getAuthToken(requestSpec);
        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .pathParam("productId", productId)
                .contentType(ContentType.JSON)
                .post(ApiEndpoints.WISHLIST_MOVE_TO_CART)
                .then().extract().response();
    }
}
