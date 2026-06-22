package backend.implementFlow;

import backend.constants.ApiEndpoints;
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

    public Response addProductToWishlist(RequestSpecification requestSpec, String productId) {
        String token = TokenManager.getAuthToken(requestSpec);
        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .pathParam("productId", productId)
                .contentType(ContentType.JSON)
                .post(ApiEndpoints.WISHLIST_PRODUCT)
                .then().extract().response();
    }

    public Response removeProductFromWishlist(RequestSpecification requestSpec, String productId) {
        String token = TokenManager.getAuthToken(requestSpec);
        return given().spec(requestSpec)
                .header("Authorization", "Bearer " + token)
                .pathParam("productId", productId)
                .delete(ApiEndpoints.WISHLIST_PRODUCT)
                .then().extract().response();
    }

    public Response moveItemToCartWhenPresent(RequestSpecification spec) {
        String productId = new DynamicProductFlow().getProductId(spec);
        addProductToWishlist(spec, productId);
        return moveItemToCart(spec, productId);
    }

    public Response moveItemToCartWhenAbsent(RequestSpecification spec) {
        String productId = new DynamicProductFlow().getProductId(spec);
        addProductToWishlist(spec, productId);
        removeProductFromWishlist(spec, productId);
        return moveItemToCart(spec, productId);
    }

    private Response moveItemToCart(RequestSpecification spec, String productId) {
        String token = TokenManager.getAuthToken(spec);
        return given().spec(spec)
                .header("Authorization", "Bearer " + token)
                .pathParam("productId", productId)
                .contentType(ContentType.JSON)
                .post(ApiEndpoints.WISHLIST_MOVE_TO_CART)
                .then().extract().response();
    }
}
